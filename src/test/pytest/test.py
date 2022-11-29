#!/usr/bin/env python3
import pytest
import requests

verbose = True
host = "http://0.0.0.0:8080" 


class TestMicronautJava:

    def test_root(self):
        path = "/"
        url = f"{host}{path}"
        print(f"url: {url}")
        result = requests.get(url)
        if verbose:
            print(result.text)
        assert result.text == '{"Status": "MicronautJava"}'

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
