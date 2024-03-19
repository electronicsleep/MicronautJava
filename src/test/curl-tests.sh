#!/bin/bash
set -ex
curl --fail -X GET http://0.0.0.0:8080/health
echo "-"
curl --fail -X GET http://0.0.0.0:8080
echo "-"
curl --fail -X POST http://0.0.0.0:8080/add-score -H 'Content-Type: application/json' -d '{"name":"chris", "score":"100"}'
echo "-"
curl --fail -X POST http://0.0.0.0:8080/add-score -H 'Content-Type: application/json' -d '{"name":"chris", "score":"90"}'
echo "-"
curl --fail -X GET "http://0.0.0.0:8080/top-score"
echo "-"
curl --fail -X GET "http://0.0.0.0:8080/text-html/chris"
echo -e "\ntests pass"
