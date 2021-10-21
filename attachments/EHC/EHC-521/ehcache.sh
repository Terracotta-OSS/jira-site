#!/bin/sh

# Please configure here
PATH=/usr/bin:$PATH
url= # e.g. http://127.0.0.1:9889/monitor

if [ $# -lt 1 ]
then
	echo "Usage: $0 [ stat | status | avgStat | aggregatedStat | probes | memory ]"
	exit 1
fi

cache=$2
stat=$3
if [ $# -ge 4 ]
then
	threshold=$4
else
	threshold=-1
fi


stat() {
	getContent "${url}/getAllCachesSampledStatisticValues?name=${stat}"
	value=`echo "${content}" | grep -A 3 "name: ${cache}" | grep -A 1 "sampledStatisticValues:" | grep "${stat}:" | awk '{if(length(DATA) > 0) DATA=DATA":"$2; else DATA=$2;} END {print DATA}'`
}

status() {
	threshold=0
	getContent "${url}/getCacheManagerStatus"
	value=`echo "${content}" | grep -A 1 "response:" | egrep -Ev "(--|response:)" | awk '{if($1 != "STATUS_ALIVE") down++;if(length(DATA) > 0) DATA=DATA":"$1; else DATA="\n|\n"$1;} END {if(down > 0) print down DATA; else print 0 DATA}'`
}

avgStat() {
	getContent "${url}/getSampledStatisticValues?cache=${cache}&name=${stat}"
	value=`echo "${content}" | grep -A 1 "response:" | grep "${stat}:" | awk '{sum+=$2} END {print int(sum/NR)}'`
}

aggregatedStat() {
	getContent "${url}/getAggregatedSampledStatisticValuesPerCache?cache=${cache}&name=${stat}"
	value=`echo "${content}" | grep -A 1 "sampledStatisticValues:" | grep "${stat}:" | awk '{sum+=$2} END {print int(sum/NR)}'`
}

probes() {
	if [ "${1}x" != "x" ]
	then
		threshold=$1
	fi
	value=`curl -s "${url}/countProbes"`
}

memorySize() {
	getContent "${url}/getCacheElementMemorySize?cache=${cache}"
	value=`echo "${content}" | grep -A 1 "response:" | grep -E "[0-9]+" | awk '{sum+=$1;if(length(DATA) > 0) DATA=DATA":"$1; else DATA="\n| "$1;} END {print sum DATA}'`
}

getContent() 
{
	if [ "${url}x" == "x" ]
	then
		echo "Please set the url variable in the $0 script!"
		exit 1
	fi
	content=`curl -s "$1"`
	exitValue=$?
}

revert=0

case $1 in
stat)
   if [ $# -lt 3 ] 
   then
	echo "Usage: $0 $1 <cacheName> <statName> [ <threshold> ]"
	exit 1
   fi
   stat
   ;;
status)
   revert=1
   status
   ;;
avgStat)
   if [ $# -lt 3 ] 
   then
      echo "Usage: $0 $1 <cacheName> <statName> [ <threshold> ]"
      exit 1
   fi
   avgStat
   ;;
aggregatedStat)
   if [ $# -lt 3 ] 
   then
      echo "Usage: $0 $1 <caheName> <statName> [ <threshold> ]"
      exit 1
   fi
   aggregatedStat
   ;;
probes)
   probes $2
   ;;
memory) 
   if [ $# -lt 2 ] 
   then
      echo "Usage: $0 $1 <cacheName> [ <threshold> ]"
      exit 1
   fi
   revert=1
   memorySize
   ;;
esac

if [ ${exitValue} -gt 0 ]
then
	exit ${exitValue}
fi
exitValue=$?
if [ $threshold -ne -1 ]
then
	test=`echo $value | grep -Eo "[0-9]+"`
	if [ $revert -eq 0 ]
	then
		if [ $test -lt $threshold ]
		then
			status="Warning: "
			exitValue=1
		else
			status="Ok: "
			exitValue=0
		fi
	else
		if [ $test -gt $threshold ]
		then
			status="Warning: "
			exitValue=1
		else
			status="Ok: "
			exitValue=0
		fi
	fi
fi

echo "${status}$1 ${stat}: $value"
exit $exitValue
