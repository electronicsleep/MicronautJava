#!/usr/bin/env python3
import pytest
import json
import requests

verbose = True
host = "http://0.0.0.0:8080" 


class Test:

    def test_root(self):
        path = "/"
        url = f"{host}{path}"
        print(f"url: {url}")
        result = requests.get(url)
        if verbose:
            print(result.text)
        assert "Home" in result.text

    def test_health(self):
        print("\n")
        path = "/health"
        url = f"{host}{path}"
        print(f"url: {url}")
        result = requests.get(url)
        if verbose:
            print(result.text)
        assert result.text == '{"Status": "Ok"}'

    def test_topscore(self):
        print("\n")
        path = "/top-score"
        url = f"{host}{path}"
        print(f"url: {url}")
        result = requests.get(url)
        if verbose:
            print(result.text)
        assert "topscore_id" in result.text

    def test_add_score(self):
        print("\n")
        path = "/add-score"
        url = f"{host}{path}"
        print(f"url: {url}")
        data = {'name': 'chris', 'score': '80'}
        headers = {'Content-type': 'application/json'}
        result = requests.post(url, data=json.dumps(data), headers=headers)
        if verbose:
            print(result.text)
        assert result.text == '{"AddScore": "Ok"}'

