Adicionar no subsystem resource-adapter a configuração para conectar ao broker remoto activemq
````xml
        <subsystem xmlns="urn:jboss:domain:resource-adapters:5.0">
            <resource-adapters>
                <resource-adapter id="activemq-rar.rar">
                    <archive>
                        activemq-rar.rar
                    </archive>
                    <transaction-support>XATransaction</transaction-support>
                    <config-property name="ServerUrl">
                        tcp://localhost:61616?jms.rmIdFromConnectionId=true
                    </config-property>
                    <config-property name="UserName">
                        admin
                    </config-property>
                    <config-property name="Password">
                        admin
                    </config-property>
                    <connection-definitions>
                        <connection-definition class-name="org.apache.activemq.ra.ActiveMQManagedConnectionFactory" jndi-name="java:/jms/remoteConnectionFactory" enabled="true" tracking="false" pool-name="RemoteConnectionFactory">
                            <xa-pool>
                                <min-pool-size>1</min-pool-size>
                                <max-pool-size>20</max-pool-size>
                                <prefill>true</prefill>
                                <is-same-rm-override>false</is-same-rm-override>
                            </xa-pool>
                            <recovery>
                                <recover-credential>
                                    <user-name>admin</user-name>
                                    <password>admin</password>
                                </recover-credential>
                            </recovery>
                        </connection-definition>
                    </connection-definitions>
                    <admin-objects>
                        <admin-object class-name="org.apache.activemq.command.ActiveMQQueue" jndi-name="java:jboss/exported/jms/queue/queue.NEWUSEREMAIL" use-java-context="true" pool-name="queue.NEWUSEREMAIL">
                            <config-property name="PhysicalName">
                                queue.NEWUSEREMAIL
                            </config-property>
                        </admin-object>
                    </admin-objects>
                </resource-adapter>
            </resource-adapters>
        </subsystem>
````


