#!/bin/sh
DAEMON=/usr/bin/mavlink-routerd
NAME=mavlink-routerd
DESC="MAVLink Router"
ARGS="-c /etc/mavlink-router/main.conf"

test -f $DAEMON || exit 0

set -e

# source function library
. /etc/init.d/functions

delay_stop() {
    count=0
    while [ $count -lt 9 ] ; do
            if pidof $DAEMON >/dev/null; then
                    sleep 1
            else
                    return 0
            fi
        count=`expr $count + 1`
    done
    echo "Failed to stop $DESC."
    return 1
}

case "$1" in
    start)
    echo -n "Starting $DESC: "
    start-stop-daemon -S -x $DAEMON -- $ARGS
    echo "$NAME."
    ;;
    stop)
    echo -n "Stopping $DESC: "
    start-stop-daemon -K --oknodo -x $DAEMON
    echo "$NAME."
    ;;
    restart)
    $0 stop
    delay_stop && $0 start
    ;;
    reload)
    echo -n "Reloading $DESC: "
    killall -HUP $(basename $DAEMON})
    echo "$NAME."
    ;;
    status)
    status $DAEMON
    exit $?
    ;;
    *)
    echo "Usage: $0 {start|stop|restart|reload|status}"
    exit 1
    ;;
esac

exit 0
