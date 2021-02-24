#export CATALINA_OPTS=&quot;-agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n&quot;
#export JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8089 ";
#export JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.ssl=false ";
#export JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.authenticate=false";
CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.port=8089 "
CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.ssl=false "
CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.authenticate=false"
