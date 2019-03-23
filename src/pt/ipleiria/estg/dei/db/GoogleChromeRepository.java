package pt.ipleiria.estg.dei.db;

import pt.ipleiria.estg.dei.model.GoogleChrome;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pt.ipleiria.estg.dei.model.BrowserEnum.CHROME;

public enum GoogleChromeRepository {
    INSTANCE;

    GoogleChromeRepository(){
    }

    public  List<GoogleChrome> getMostVisitedSite() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection(CHROME);
        ResultSet rs;
        Statement statement = connection.createStatement();
        List<GoogleChrome> histories = new ArrayList<>();
        rs = statement.executeQuery("SELECT  urls.url as url, count(*) as visit" +
                " FROM urls, visits " +
                " WHERE urls.id = visits.url " +
                " GROUP by urls.url " +
                " ORDER By visit_count DESC "
                + "LIMIT 5;");

        while (rs.next()) {
            String url = rs.getString("url");
            int visitNumber = Integer.parseInt(rs.getString("visit"));
            histories.add(new GoogleChrome(url, visitNumber));
        }

        //Exemp only (pls don´t put me on a weird chat :)
        BroserHistoryDBArrangement.insertDomain(histories);

        return histories;
    }

    public  List<GoogleChrome> getBlockedSitesVisited(HashMap blokedWebsites) throws Exception {
        Connection connection = ConnectionFactory.getConnection(CHROME);
        ResultSet rs;
        Statement statement = connection.createStatement();
        List<GoogleChrome> histories = new ArrayList<>();
        rs = statement.executeQuery(" SELECT replace( SUBSTR( substr(url,instr(url, '://')+3), 0,instr(substr(url,instr(url, '://')+3),'/')), 'www.', '') as urlDomain, sum(visit_count) as visitDomain " +
                " FROM urls " +
                " GROUP BY urlDomain " +
                " order by visitDomain desc ");

        HashMap blockedWebisites = blokedWebsites;

        while (rs.next()) {

            String url = rs.getString("urlDomain");

            if(blockedWebisites.containsKey(url)){
                int visitNumber = Integer.parseInt(rs.getString("visitDomain"));
                histories.add(new GoogleChrome(url, visitNumber));
            }
        }
        return histories;
    }

    public List<String> getWordsFromGoogleEngine() throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        Connection connection = ConnectionFactory.getConnection(CHROME);
        ResultSet rs;
        Statement statement = connection.createStatement();
        List<String> histories = new ArrayList<>();
        rs = statement.executeQuery("SELECT substr(url, instr(url, '?q=')+3) as word  " +
                "FROM urls " +
                "where url like '%google.%' and url like '%?q=%'");

        while (rs.next()) {
            String encoded = rs.getString("word");
            String substring = encoded.substring(0, encoded.indexOf("&"));
            String decode = URLDecoder.decode(substring, "UTF-8");
            histories.add(decode);
        }
        return histories;
    }

    public HashMap<String, Integer> getEmailsFromHistory() throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        Connection connection = ConnectionFactory.getConnection(CHROME);
        ResultSet rs;
        Statement statement = connection.createStatement();

        //Only need to replace character present in emails (decoding it with java function for some reason leads to errors after a while)
        rs = statement.executeQuery("select replace(url, " +
                "    '%40', '@') as email, replace(title, " +
                "    '%40', '@') as email2" +
                " from urls " );

        HashMap<String, Integer> historicoEmail = new HashMap();
        Pattern emailVerification = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");

        while (rs.next()) {
            String emailUrl = rs.getString("email");
            Matcher m = emailVerification.matcher(emailUrl);
            String group;
            while (m.find()) {
                group = m.group();
                if (historicoEmail.containsKey(group) == true) {
                    historicoEmail.put(group, historicoEmail.get(group) + 1);
                } else {
                    historicoEmail.put(group, 1);
                }
            }

            String emailTitle = rs.getString("email2");
            m = emailVerification.matcher(emailTitle);
            while (m.find()) {
                group = m.group();
                if (historicoEmail.containsKey(group) == true) {
                    historicoEmail.put(group, historicoEmail.get(group) + 1);
                } else {
                    historicoEmail.put(group, 1);
                }
            }
        }
        return historicoEmail;
    }

    public HashMap<String, List<GoogleChrome>> getDomainsByFrequencyPerDay() throws SQLException, ClassNotFoundException, ParseException {
        Connection connection = ConnectionFactory.getConnection(CHROME);

        Statement statement = connection.createStatement();
        HashMap<String, List<GoogleChrome>> histories = new HashMap<>();

        ResultSet rs = statement.executeQuery(
                "SELECT strftime('%d-%m-%Y', datetime(((visits.visit_time/1000000)-11644473600), 'unixepoch')) as dat, " +
                            "replace( SUBSTR( substr(u.url,instr(u.url, '://')+3), 0,instr(substr(u.url,instr(u.url, '://')+3),'/')), 'www.', '') as domain, " +
                            "SUM(visit_count) n_visit " +
                    "FROM urls u " +
                    "LEFT JOIN visits on u.id = visits.url " +
                    "GROUP BY dat, domain " +
                    "ORDER BY datetime(((visits.visit_time/1000000)-11644473600), 'unixepoch') DESC;");

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

        while (rs.next()) {
            String date = rs.getString("dat");
            String domain = rs.getString("domain");
            String numberOfVisits = rs.getString("n_visit");

            List<GoogleChrome> googleChromes = histories.get(domain);
            if (googleChromes == null) {
                googleChromes = new ArrayList<>();
            }
            Date parse = date!= null ?formatter.parse(date): null;
            int i = numberOfVisits != null ? Integer.parseInt(numberOfVisits): 0;
            googleChromes.add(new GoogleChrome(domain, i, parse));
            histories.put(domain, googleChromes);
        }
        return histories;
    }

    public float  getDaysDatabaseWasActive() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection(CHROME);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT julianday(MAX(datetime(((visits.visit_time/1000000)-11644473600), 'unixepoch'))) - julianday(MIN(datetime(((visits.visit_time/1000000)-11644473600), 'unixepoch'))) as days " +
                "from visits;");
        String days="";
        while (rs.next()) {
            days = rs.getString("days");
        }
        return Float.valueOf(days);
    }
}
