#!/bin/bash
set -ex
URL=http://0.0.0.0:8081
curl --fail -X GET $URL/health
echo "-"
curl --fail -X GET $URL
echo "-"
curl --fail -X POST $URL/add-score -H 'Content-Type: application/json' -d '{"name":"chris", "score":"100"}'
echo "-"
curl --fail -X POST $URL/add-score -H 'Content-Type: application/json' -d '{"name":"chris", "score":"90"}'
echo "-"
curl --fail -X GET $URL/top-score
echo "-"
curl --fail -X GET $URL/text-html/chris
echo -e "\ntests pass"
