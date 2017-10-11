# bonita-page-authorization-rules
This project provides an example of page mapping authorization rule for bonita 7.3 and above.
This mechanism is used to specify which users will be allowed to view a process/task form or a case overview page.  

This simple rule (HasProfileRule) allows a user with a given profile (configurable in bonita-tenants-custom.xml) to access the form/page.  
An example of configuration using this rule can be found in the directory conf/ of this project.  

For more information Refer to the [official documentation](https://documentation.bonitasoft.com/?page=custom-authorization-rule-mapping).
