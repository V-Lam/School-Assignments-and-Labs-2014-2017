#!/bin/sh
#
echo
hour=`date +%H`
if [ �$hour� -lt 12 ]
then
  echo �GOOD MORNING�
elif [ �$hour� -lt 18 ]
  then
    echo �GOOD AFTERNOON�
  else
    echo �GOOD EVENING�
fi
echo
