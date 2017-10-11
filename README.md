# bonita-page-authorization-rules

## Overview
This project provides an example of page mapping authorization rule for bonita 7.3 and above.
This mechanism is used to specify which users will be allowed to view a process/task form or a case overview page.  

This simple rule (HasProfileRule) allows a user with a given profile (configurable in bonita-tenants-custom.xml) to access the form/page.  
An example of configuration using this rule can be found in the directory conf/ of this project.  

For more information Refer to the [official documentation](https://documentation.bonitasoft.com/?page=custom-authorization-rule-mapping).

## Build
Having a JDK 1.7 or > and Maven 3 installed Just run the command :  
    
    mvn clean package
    
## Usage
- Retrieve the jar file in the target/ directory of the project and put it at the same place as bonita-server-*.jar
- Using the setup tool to retrieve and push your tenant configuration: 
  - declare the 2 beans in bonita-tenants-custom.xml (just like it is done in the example provided in the conf directory of this project)
  - set the property `bonita.tenant.authorization.rule.mapping` to `authorizationRuleMappingWithProfile` in bonita-tenant-community-custom.properties
