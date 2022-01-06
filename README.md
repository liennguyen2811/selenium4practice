Open Weather Map Automation
==========================

### How do I get set up?
* Install [Amazon Coretto 11](https://aws.amazon.com/corretto/)
* Install [InteliJ IDEA Community Edition](https://www.jetbrains.com/idea/download)
* Install [git](https://git-scm.com/downloads)
* Clone the project from the git repo
* Open the project using Intellij
* Go to the pom.xml file, right click, select Maven then Reimport
* Download [selenium drivers](https://selenium.dev/documentation/en/webdriver/driver_requirements/#quick-reference)
and add them in the bin/{```your_OS```} folder.
* Install maven to run test in command line

### How to run the tests?

#### Before running
This project uses the following list of properties:

* ```hub``` can be set to:
    * ```local``` (default value) - runs the test on your local machine
    * ```browserstack``` - runs the tests on browserstack
    * ```grid``` - runs the the tests on Selenium  Grid
* ```gridIp``` and ```gridPort``` can be set with the IP and port of the Selenium Grid
* ```testEnvironment``` can be set to:
    * ```dev``` - runs the tests against the dev environment
    * ```test``` (default value) - runs the tests against test environment
    * ```uat``` - runs the tests against uat environment
* ```testBrowser``` is the browser you want to run the test on and can be set to:
    * ```firefox```
    * ```chrome``` (default value)
    * ```safari```
    * ```edge```
    * ```ie```
* Browserstack specific: ```bsBrowser, bsBrowserVersion, bsOs, bsOsVersion, bsResolution```, using
 values from [bs_capabilities](https://www.browserstack.com/automate/java#setting-os-and-browser). They are taken into consideration only when you run the tests on Browserstack.
*

#### Local machine
* Right click on the testng.xml file and then select ```Edit...``` or right click on a test and the click on ```Create...```
* On the VM options add options using ```-D flag```:  e.g. -Dhub=browserstack -DuseTestrail=yes -DtestPlanId=389 -DtestEnvironment=test
* Click OK
* Click the play button
* This will start running all the tests on your local machine using Chrome browser

###### To run project at command line**
* Get project from git repository
* Navigate to repository location
* Run: mvn install -DtestEnvironment=test -Dtestnames=SmokeTest -DtestBrowser=chrome

#### Jenkins
* This needs to be created by your team
* Use the above properties to make the input
  on jenkins
* Will run on slave (Jenkins node) to distribute test suite
* Document to setup jenkin and itsjob https://docs.google.com/document/d/1nbVJC_CzDRjeyHytQkoNqR9t77mE7veTac5vPH_d7G0/edit?usp=sharing


