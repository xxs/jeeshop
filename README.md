Jeeshop (Work In Progress)
=======

# Description
Jeeshop is a e-commerce solution allowing you to setup quickly your store using innovative technologies such as:
* AngularJS
* Java EE 7
* Java 8
# Modules
TODO
# Deployment
Jeeshop components can be deployed to any Java EE 7 compatible server.
## Wildfly 8
### Datasources
The following XA datasources are currently used by jeeshop modules and have to be created in server configuration
* JeeshopDS

Sample of configuration for a standalone server with datasources referencing a single jeeshop database:
  ```xml
  <xa-datasource jndi-name="java:/JeeshopDS" pool-name="JeeshopDS" enabled="true">
      <xa-datasource-property name="ServerName">
          localhost
      </xa-datasource-property>
      <xa-datasource-property name="DatabaseName">
          jeeshop
      </xa-datasource-property>
      <driver>mysql</driver>
      <security>
          <user-name>jeeshop</user-name>
          <password>test</password>
      </security>
      <validation>
          <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
          <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
      </validation>
  </xa-datasource>
  <driver name="mysql" module="com.mysql">
      <driver-class>com.mysql.jdbc.Driver</driver-class>
      <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
  </driver>
  ```
  

### Security domain configuration
A security domain named "jeeshop" has to be created to allow BASIC authentication and Role based access to protected REST Resources, using JaaS.

TODO, documentation of SSL configuration.

Sample of configuration for a standalone server:
  ```xml
  <security-domain name="jeeshop">
      <authentication>
          <login-module code="Database" flag="required">
              <module-option name="dsJndiName" value="java:/JeeshopDS"/>
              <module-option name="principalsQuery" value="select password from User where login = ?"/>
              <module-option name="rolesQuery" value="select name,'Roles' from Role r, User_Role ur, User u where u.login=? and u.id = ur.userId and r.id = ur.roleId"/>
              <module-option name="hashAlgorithm" value="SHA-256"/>
              <module-option name="hashEncoding" value="base64"/>
              <module-option name="unauthenticatedIdentity" value="guest"/>
          </login-module>
      </authentication>
  </security-domain>
  ```

### JBOSS Modules
A JBOSS Module named "jeeshop" have to be created to <WILDFLY HOME>/modules directory.
It contains multiple configuration properties such as:
* Mailer (SMTP parameters, Sender...)
Sample of this module configuration is available in .openshift directory

## Database setup
Database installation scripts are provided in ./install/db directory

* jeeshop-install.sql contains table creation instructions. It also creates a single user with login/password admin/jeeshop (password is hashed using SHA-256 in this script, which must match security domain configuration, see section above)
* jeeshop-drop.sql empties database
* demo-catalog-data contains jeeshop demonstration catalog data

Notes:
By default, all tables are created in a single script, ie for a single database referenced in server datasources configuration. See "Datasources" section above.
