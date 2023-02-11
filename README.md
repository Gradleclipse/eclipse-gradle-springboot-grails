# eclipse-gradle-springboot-grails

<g-emoji class="g-emoji" alias="radioactive" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/2622.png">☢️</g-emoji> 
OUT-DATED 
<g-emoji class="g-emoji" alias="radioactive" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/2622.png">☢️</g-emoji>

Because a Guide with versions will decay rapidly, this Guide is outdated!

E.g.: Grails 5 supports now JDK 17, which was not the case, when this guide was created - well sometimes the world becomes better ;-)

## Updated Dependencies for Grails 5.3.0

See https://docs.grails.org/5.3.0/guide/single.html
### Grails 5.3.0 ships with the following dependency upgrades:
- Groovy 3.0.11
- Micronaut 3
- Micronaut for Spring 4
- GORM 7.3.2
- Spring Framework 5.3
- Spring Boot 2.7
- Gradle 7.2
- Spock 2.1-groovy-3.0
- Grails Testing Support 2.4.1


## Disclaimer
The Author or this Project is not connected to the Eclipse-Foundation or the Gradle Project in any way.

I am a fan of eclipse and that's all.

## License
This project is under Apache License Version 2.0

See the file "LICENSE"

## Preface
If you already use IntelliJ IDEA you <i>really don't need</i> this project!

But if you also like the Eclipse IDE you may read on.

Eclipse lacks Grails-Support, but it has a good Gradle (and Spring-Boot) Integration - let's use it
for a Gradle-SpringBoot-Grails5 Project!

The Project eclipse-gradle-springboot-grails was created as a gradle project within eclipse by Buildship.

See also: https://www.vogella.com/tutorials/EclipseGradle/article.html

## It's not a new idea ...
At June 2019 Bhagvan Kommadi has already given us an in-depth Guide how to develop grails 3 with Gradle.
He also used the Groovy Eclipse plugin. To gain more insight see:
https://examples.javacodegeeks.com/core-java/gradle/gradle-grails-example/

## Howto run the application with the Eclipse-IDE in Debug-Mode
- Run > Run Configurations... > Gradle Task: bootRun
    - See Launch-Configuration file: "eclipse-runconfigurations\eclipse-gradle - bootRun.launch"
    - Under Environment Development the Java-Application will stop with the log-message
      <pre>Listening for transport dt_socket at address: 5555</pre>
- Debug Configurations... > Remote Java Application > "[Remote Debug] eclipse-gradle-springboot-grails" 
    - After that your Application should be running in Debug-Mode
    - And you should see a log-message like:
      <pre>Grails application running at http://localhost:8080 in environment: development</pre>
    - The Application will stop again in Debug-Mode, so click the "Resume-Button"
    - Now you can set Breakpoints and can Debug your Application
    
### How we achieved this behaviour...
- In build.gradle under Section bootRun {...} we added
    <pre>
        jvmArgs( '-Xdebug','-agentlib:jdwp=transport=dt_socket,address=5555,server=y,suspend=y'	)
    </pre>
    - This prepared "gradle bootRun" for Remote-Debugging with port 5555

## How to deploy and run the grails application
- In the project-dir you will find a Grails-Wrapper
- Under Windows open a Windows-Command Prompt and change to your project-directory
- Do ".\grailsw clean" (this will take some time)
- Do ".\grailsw package" (this will take some time)
- Under build\libs you should find a War Archive-file [project-name].war, e.g.: 'eclipse-gradle-springboot-grails.war'    
- create a sub-directory "buildrun" and copy the war-archive into it
- Change to the sub-directory "buildrun" and do something like the following:
<pre>
	java -Dgrails.env=prod -jar eclipse-gradle-springboot-grails.war
</pre>

## Our intended Development-Cycle (Which FAILED dramatically!)
- Run your application in Debug-Mode described as above
- Change some Source-Code say you changed your HelloWorldController.groovy
- The file will be compiled new
- Next we do ```.\gradlew restart```
- Because We added a Gradle-Restart-Task that will restart your server-application
- But You will NOT SEE ANY CHANGES you made - why?
The crucical point here is, that hot-code-replacement will not work within eclipse! (together with JDK15)
And even your webapplication has made a restart, it HAD NOT loaded the Classes aNew - although the classes are lying new compiled on the file-system
( more precisely they were new compiled in the build-folder).
<b>This is super-annoying</b>, but it's for real now.</br>
</br>
So you have to make hot-code-replacement work if you want a really smooth development cycle.
Until this happens you have to do a cold-restart of your webapplication - what a mess.


### How we added the Gradle-Restart-Task
- in ``build.gradle`` add the following dependencies
<pre>
    implementation 'org.springframework.boot:spring-boot-starter-actuator'	
    implementation 'org.springframework.cloud:spring-cloud-starter:3.1.3'
    developmentOnly("org.springframework.boot:spring-boot-devtools")
</pre>
- Note: The spring-boot-devtools didn't work out of the box
- but with the spring-boot-actuator and spring-cloud-starter dependencies we have a restart-endpoint
    - ``curl -X POST http://localhost:8080/actuator/restart`` will provoke a server-restart!
- This is what your Restart-Task does, but because this Gradle-Task depends on compileGroovy and compileJava your project will be compiled too!
- Howto implement the Gradle-Restart-Task see ``task restart`` in build.gradle
- See also the Groovy-Implementation for the Http-Post on the Restart-Point: ``en.example.gradle.RestartTask.groovy``


## Why not using a [Spring Tool Suite] Runconfiguration?
Unfortunately using a Run-Configuration with the Spring Tool Suite will  _NOT_  work!

You could install Spring Tool Suite 4 under Eclipse and then Debug your "Spring-Boot-Application" 
with a "Spring Tool Suite Run-Configuration", e.g. see: eclipse-runconfigurations/[Spring Tool Suite] eclipse-gradle.launch

### Why will this not work?
Because a Spring-Boot Application is  _not_   a Grails-Application!
One Symptom is, that your Grails-Application Configuration-Files  _application.groovy_ ,  _runtime.groovy_  will not be loaded!
So the behaviour is "wrong" with unpredictable results :-(

### Spring Tool Suite 4
To install the Spring Tool Suite in your Eclipse IDE, e.g. see:
https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4


## How to use this Project
If you like to develop a Grails5 Application within Eclipse, this project maybe your starting point.
- Check the project out
- Rename the project to your needs, maybe you want change the default-package 'en.example' too
- Delete the .git folder and check it in your Versioning-Software (Git, SVN, CVS, what-so-ever)
- What you need can be read under Section "Recommended Software"
- Add the following to your eclipse.ini after the -vmargs line

    -Dgroovy.antlr4=true    
- See also Section "Installation of JDK15"  

The most part of this readme-file is about how this project was created, so you may don't need to care about all information written down here.


## Which OS?
<b>Note:</b> This project was created under Windows and most tips are related to windows.
But there is no reason, why this shouldn't work under Linux as well.

## No GSP-Editor
At this time i don't know a good GSP-Editor for Eclipse, but what i do is:
- Window > Preferences > General > Editors > File Association: Add "*.gsp"
    - Add to Associated Editors: XML Editor

For me this is good enough. 

( Of course IntelliJ has a great Grails- and GSP-Support. )

( Or maybe you are using thymeleaf anyway? ^^ )

## Recommended Software
If you use the following software you should have no problems running this project:
- Oracles Open JDK 15, download: https://jdk.java.net/archive/
- Eclipse IDE for Enterprise Java and Web Developers Version 2022-03 (4.23.0)
    - Download: https://www.eclipse.org/downloads/packages/
    - Additionally you need the following Eclipse-Plugins:
        - Buildship: Eclipse Plug-ins for Gradle (should already be integrated)
        - Eclipse Groovy Develpment Tools Version 4.5.0
            - Groovy Compiler 3.0
    - Spring Tools Suite Version 4

## Which JDK should we use
The short answer is: Use the JDK which is supported by ALL your software-components.

The longer answer is: <b>Use JKD version < 16 see down under!

## Groovy 3.0 does not support JDK16
See: https://issues.apache.org/jira/browse/GROOVY-10145
So our best bet is to use JDK9-JDK15!

## gradle build fails with "java.lang.reflect.InvocationTargetException"
If you see this error-message this may have also something do to with JDK >= 16

E.g. see Issue: https://github.com/erdi/webdriver-binaries-gradle-plugin/issues/30

### Installation of JDK15
- Download e.g. Oracles Open JDK: https://jdk.java.net/archive/
- After installing Eclipse jee-2022-03-R (or later)
  edit eclipse.ini, look after <b>-vm</b> line and exchange a line of the following type:
<pre>
	plugins/org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.2.v20220201-1208/jre/bin
</pre>
through a line similar to this:
<pre>
    C:/java/jdk-15.0.2/bin
</pre>
( you have to adapt the path to your JDK-Installation of course )

## It's all about the right versions
To use the right versions is crucial, here they are:
- Eclipse jee-2022-03-R (or later)
- Groovy Development Tools Release 4.5.0
- Spring Boot 2.6.5
- Groovy 3.0.10
     - Eclipse > Windows > Preferences > Groovy > Compiler > Switch-to Groovy compiler version 3.0.10 (+restart eclipse)
     - And therefore we have to use an appropriate JDK, e.g. JDK15
- Grails 5.1.7

In the future you may deduce your versions the other way around:

    The Grails version deduce the rest!

## Spring-Boot Version determines Groovy-Version
Spring-Boot 2.6.5 needs Groovy 3.0.10 - so keep in mind, that the Spring-Boot version determines the Groovy-Version!
### Spock-Version is determined by the Groovy-Version
The Groovy-Version determines the Version for Spock-Core, e.g. we take the following version

    testImplementation 'org.spockframework:spock-core:2.1-groovy-3.0'

If you do
    
    .\gradlew dependencies > gradle.dependencies.txt
    
then you will see e.g. the following: Spring-Boot 2.6 needs Groovy 3.0 :-)

## Using gradle version 7
### Question
I got an error-message:
``Caused by: org.gradle.api.artifacts.UnknownConfigurationException: Configuration with name 'runtime' not found.``
### Answer
In Gradle 7, the configurations 
compile, testCompile, runtime and testRuntime have been removed in favor of 
implementation, testImplementation, runtimeOnly and testRuntimeOnly. 
That's maybe the reason why Gradle issues.

## Refresh Gradle Project
If you have new dependencies do "Gradle > Refresh Gradle Project" !

## Lombok integration for eclipse
see: https://projectlombok.org/setup/eclipse
- Download the newest lombok.jar and double-click it

## Lombok Integration for gradle
In your build.gradle add

	compileOnly "org.projectlombok:lombok:1.18.22"	// We love lombok!
	annotationProcessor "org.projectlombok:lombok:1.18.22" // Your annotation-processor
	// Annotation: "@Slf4j"
	implementation('org.slf4j:slf4j-api')
	
## Use Lombok only for Java-Classes
<b>Hint:</b> Don't use Lombok within Groovy-Classes - you will get Compiler-errors.

Just use Lombok with Java-Classes and you are fine!	
	
## Adding Groovy Support
### Add Groovy Support to eclipse
See: https://github.com/groovy/groovy-eclipse/wiki/4.5.0-Release-Notes

Add the following to your eclipse.ini after the -vmargs line

    -Dgroovy.antlr4=true

### Add Groovy Support to Gradle
See: https://docs.gradle.org/current/userguide/groovy_plugin.html
Simply add the Groovy-Plugin to your build.gradle

    plugins {
    	id 'groovy'
	}

#### Groovy Version
Note: The Version of the Gradle Groovy-Plugin is already determined by Gradle, so you may not write a version here 

## Add Spock-Test Support
In your build.gradle the following entry is required to let find you any (Spock-)test:

    // Spock 2 is based on JUnit Platform which needs to be enabled explicitly.
    tasks.withType(Test).configureEach {
	     useJUnitPlatform()
    }

## Upgrade your wrapper to the current version Eclipse is using
For example do in your project-dir:

    ./gradlew wrapper --gradle-version 7.4.2

## About gradle-Runconfigurations within Eclipse
<b>Warning:</b> It seem's that Eclipse don't let you customize the Gradle-Task in his Run-Configurations as you might expect.

### Example
The following Runconfiguration will NOT work:

	Run Configurations... > Gradle Taks > Gradle Task: test > 
	> Project Settings: Override Project Settings activated >	                                                           
     > Programm Arguments: --tests "en.example.simple.SimpleSpec" --stacktrace
     
Because Eclipse does some silly things with these arguments.

### For single Spock-Tests use the Gradle-Test Runconfigurations
With these Gradle-Test Runconfigurations you can run a single test and you can DEBUG them with it!

## Add Grails to your project
### Grails 5 Dependencies
See: https://docs.grails.org/5.1.7/guide/single.html#dependencyUpgrades
Grails 5.1.7 ships with the following dependency upgrades:
- Groovy 3.0.7
- Micronaut 3
- Micronaut for Spring 4
- GORM 7.2.0
- Spring Framework 5.3
- Spring Boot 2.6
- Gradle 7.2
- Spock 2.0-groovy-3.0
- Grails Testing Support 2.3.0
Fortunately this seems to fit with our already present versions!
(Otherwise you would have to adapt...)

### Init a new small grails webapp project
<pre>
curl -O https://start.grails.org/mygrails5.1.7webapp.zip -d features=events,geb2,hibernate5,json-views,less-asset-pipeline,markup-views
</pre>

Unzip the archive and copy&paste the folder to your existing gradle-project

### folder grails-app
- Don't forget to copy also the folder grails-app to your existing gradle-project ;-)
- Change the package-structure within folder grails-app to your needs ( the default may not what you want )

### Edit gradle.properties
- Change groovyVersion=3.0.7 to 3.0.10 (according to your eclipse groovy-compiler!)

### Merge build.gradle
This may be the most important step:
- Merge the build.gradle from your new grails-project into your existing build.gradle from your Gradle-Project  

### FileNotFoundException: build/.grailspid
When Starting the SpringBoot-Application via the Eclipse Spring-Tools-Suite you may get the following error-message:
<pre>
java.io.FileNotFoundException: .\build\.grailspid
</pre>
This is, because there is no directory ./build in your project.
Create it or make a ".\gradlew build"

We solved this problem by adding this to our build.gradle:
<pre>
tasks.register('ensureBuildDirectory') {
	doLast {
		println "--> ensureBuildDirectory: mkdir ${buildDir}"
		mkdir "${buildDir}"
	}
}
clean.finalizedBy('ensureBuildDirectory')
</pre>

## Eclipse Runconfigurations
See project-folder: "eclipse-runconfigurations"

Have fun!

                                    



