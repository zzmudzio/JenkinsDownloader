# JenkinsDownloader

This is a simple project that helps me to automate some boring tasks, such as logging into jenkins to download an actual build of the application. 
I work as a QA, my team is taking care of about 10 applications. I often have to download, download and download next builds of an application. It's a quite boring task so I've decided to create an app that does this for me. Major tasks of this program are:
<ul>
<li>sign in into jenkins with credentials which I provide during the code execution</li>
<li>find an app which name I've provided,</li>
<li>download,</li>
<li>unzip to desired directory(overwriting existing files)</li>
<li>put an information about downloaded version and date into *xml file</li> 
</ul>

The process of saving data into xml file is performed by my other app, using JAXB. JenkinsDownloader just runs that program and provides needed data. 

I want to have a control of which build I currently use so I store the information inside xml file. It is impossible to remember that when having about 10 apps, each in different version. The xml file can be read both using my second program and just e.g. IE. 

The current project structure is as shown below: 

![projectStructure](https://user-images.githubusercontent.com/99602564/203613859-af450bfb-f4a9-4dea-affa-900fa8f1691b.png)

Appliaction has been created just for my personal use. It is impossible to be used by third party, because of particular apps it is developed to download. I used this project to consolidate knowledge about Selenium WebDriver, TestNG etc. 


