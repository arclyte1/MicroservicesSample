import time
import pytest
import requests
import json


client_base_url = 'http://localhost:8081/'
film_session_base_url = 'http://localhost:8080/'


@pytest.mark.run(order=1)
def test_create_film_sessions():
    url = film_session_base_url + "film_session"
    for i in range(1, 10):
        payload = {
            "title": f"test{i}",
            "price": 500 * i,
            "date": f"2023-12-0{i}T11:30:00.000+00:00"
        }
        headers = {
            "Content-Type": "application/json"
        }

        response = json.loads(requests.request("POST", url, headers=headers, data=json.dumps(payload)).text)
        response.pop("id")

        assert response == payload


@pytest.mark.run(order=2)
def test_retrieve_film_session():
    url = film_session_base_url + "film_session/1"
    response = requests.request("GET", url, headers={}, data={})
    assert response.status_code == 302


@pytest.mark.run(order=3)
def test_update_film_session():
    url = film_session_base_url + "film_session/2"
    payload = {
        "title": "1234",
        "price": 20000,
        "date": "2023-12-08T11:30:00.000+00:00"
    }
    headers = {
        "Content-Type": "application/json"
    }
    response = json.loads(requests.request("PUT", url, headers=headers, data=json.dumps(payload)).text)
    response.pop("id")
    assert response == payload


@pytest.mark.run(order=4)
def test_delete_film_session():
    url = film_session_base_url + "film_session/1"
    response = requests.request("DELETE", url, headers={}, data={})
    assert response.status_code == 200


@pytest.mark.run(order=5)
def test_retrieve_film_sessions_by_ids():
    url = film_session_base_url + "film_session"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "ids": "2,3,4"
    }
    response = json.loads(requests.request("GET", url, headers=headers, params=params).text)
    assert len(response) == 3


@pytest.mark.run(order=6)
def test_retrieve_film_sessions_by_date_range():
    url = film_session_base_url + f"film_session"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "start_date": "2023-12-02",
        "end_date": "2023-12-05"
    }
    response = json.loads(requests.request("GET", url, headers=headers, params=params).text)
    assert len(response) == 2


@pytest.mark.run(order=7)
def test_create_client():
    url = client_base_url + "client"
    payload = json.dumps({
        "phone": "+79998887767"
    })
    headers = {
        "Content-Type": "application/json"
    }
    response = json.loads(requests.request("POST", url, headers=headers, data=payload).text)
    
    assert response["phone"] == "+79998887767"


@pytest.mark.run(order=8)
def test_visit_film_session():
    url = client_base_url + "client/visit"
    payload = json.dumps({
        "clientId": 1,
        "filmSessionId": 2
    })
    headers = {
        "Content-Type": "application/json"
    }
    response = requests.request("POST", url, headers=headers, data=payload)
    
    assert response.status_code == 200


@pytest.mark.run(order=9)
def test_retrieve_client_with_status():
    url = client_base_url + "client/1"
    headers = {
        "Content-Type": "application/json"
    }
    response = json.loads(requests.request("GET", url, headers=headers).text)

    assert response["clientStatus"] == "PLATINUM"

