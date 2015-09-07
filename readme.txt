wildfly 9 configuration file

<security-domain name="securityexampledomain" cache-type="default">
                    <authentication>
                        <login-module code="Database" flag="required">
                            <module-option name="dsJndiName" value="java:/PostgresLibaryUl"/>
                            <module-option name="principalsQuery" value="SELECT password FROM accounts WHERE login=?"/>
                            <module-option name="rolesQuery" value="SELECT role, 'Roles' FROM accounts WHERE login=?"/>
                        </login-module>
                    </authentication>
                </security-domain>

<datasource jta="true" jndi-name="java:/PostgresLibaryUl" pool-name="PostgresLibaryUl" enabled="true" use-ccm="true">
                    <connection-url>jdbc:postgresql://localhost:5432/libaryUl</connection-url>
                    <driver-class>org.postgresql.Driver</driver-class>
                    <driver>postgresql-9.3-1102-jdbc3.jar</driver>
                    <security>
                        <user-name>****</user-name>
                        <password>****</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
                        <background-validation>true</background-validation>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
                    </validation>
                </datasource>