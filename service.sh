#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Keep the pwd in mind!
# Example: RUN="java -jar $DIR/target/bus-route-service-1.0.0-RELEASE.jar"
RUN="java -jar $DIR/target/bus-route-service-1.0.0-RELEASE.jar"
NAME=bus-route-service

DATA_FILE=$2

PIDFILE=/tmp/$NAME.pid
LOGFILE=/tmp/$NAME.log

start() {
    if [ -f $PIDFILE ] && kill -0 $(cat $PIDFILE); then
    echo 'Service already running' >&2
    return 1
  	fi
    if [[ -z "$DATA_FILE" ]]; then
    	echo "Usage: bash service.sh start FILE. Bus route data file path is mandatory."
    	return 1
    fi
    
    if [ -f "$DATA_FILE" ]; then
    	local CMD="$RUN $DATA_FILE &> \"$LOGFILE\" & echo \$!"
		sh -c "$CMD" > $PIDFILE
		sleep 5s
		response=$(curl -X POST -i -H "Content-type: application/json" http://localhost:8080/api/route/data/load -d C:/personnal/Personnal/tests/goeuro-dev-test/test-routes --write-out '%{http_code}' --silent --output /dev/null)
		echo $response
		if [ "$response" != "200" ]; then
			kill -15 $(cat $PIDFILE) && rm -f $PIDFILE
			echo "Bus route data file is not valid. Service not started."
		fi
		return 1
 	else
		echo "$DATA_FILE not found. Service not started."
		return 1
	fi
    
}

stop() {
    if [ ! -f $PIDFILE ] || ! kill -0 $(cat $PIDFILE); then
        echo 'Service not running' >&2
        return 1
    fi
    kill -15 $(cat $PIDFILE) && rm -f $PIDFILE
}


case $1 in
    start)
        start
        ;;
    stop)
        stop
        ;;
    block)
        start
        sleep infinity
        ;;
    *)
        echo "Usage: $0 {start|stop|block} DATA_FILE"
esac