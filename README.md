# Browser History Histogram for Autopsy

Ingest module for the Autopsy Forensic Analysis platform, developed in JAVA, within the scope of the Computer Science 
Degree of the 'Escola Superior de Tecnologia e Gestão do Instituto Politécnico de Leiria', Portugal

The main goal of this module is to extract all webactivity of a user to a local database and then generate a report to display this information.
This module runs on windows and linux and extract information of Google Chrome and Firefox. You can run it as a [Autopsy](https://www.autopsy.com/)
module or without any dependencies.

## Getting Started

### Prerequisites
* JDK 8 (mandatory)
* [Autopsy](https://www.autopsy.com/) (Optional - to run it  as a autopsy module) 
* Ant, ivy (Optional - if you want to contribute)

### Installing
You have 2 options to use this module:
* As a [Autopsy](https://www.autopsy.com/)  module
    1. Download Labcif-bhh-autops.nbm
    2. Tools - Plugins - Downloaded - Add Plugins... 
    3. Select Labcif-bhh-autops.nbm downloaded previously
    4. Click Install - Next - Check terms acceptance - Install - Continue - Finish
    
    
* Without any external dependencies 
    1. Download Labcif-bhh.jar

### Run
* As a [Autopsy](https://www.autopsy.com/)  module
    1. Running the ingest module will create a database browser-history.db in the same directory as the case opened
        1. Tools - Run Ingest Modules - "Select image" Ex: PC01.EO1
        2. Select Labcif - Browser History Histogram
        3. Finish
    2. After running the ingest module, you will be able to run the report Module. 
        1. Generate Report
        2. Select Labcif - Browser History Histogram
        3. Finish


## Authors

* **Kevin Baptista**
* **Tomás Honório**
* Work developed under the guidance and coordination of Professors **Patrício Domingues** and **Miguel Frade**


