## Drones

[[_TOC_]]

---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task
REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task.

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

#### Functional requirements

- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

- All functional requirements implemented.

---

### Dependencies Used
- Spring Starter Web
- Lombok
- Spring Starter Validation
- Devtools
- H2 Database
- Spring Data JPA
- Spring Test

---

### DRONE API Endpoint
#### - REGISTERING A DRONE
- Endpoint http://localhost:2023/api/v1/musalasoft/drone/
- Method - POST
- Response - JSON

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

**POSTMAN Raw Body**

```
{
"serial": "12345678",
"model": "drone-model-AA3",
"weightLimit": 20,
"batteryCapacity": 80,
"state": "IDLE"
}
```

**RESPONSE RESULT**

```
    {
    "data": {
        "droneId": 1,
        "serial": "12345678",
        "model": "drone-model-AA3",
        "weightLimit": 20,
        "batteryCapacity": 80%,
        "state": "IDLE"
        },
    "message": "Drone Registered Successfully!",
    "status": 200
    }
```

---

#### - CHECKING AVAILABLE DRONES FOR LOADING
- Endpoint http://localhost:2023/api/v1/musalasoft/drone/loading
- Method - GET
- Response - JSON

Returns list all available drones for loading in JSON format


---
#### - CHECK DRONE BATTERY LEVEL FOR A GIVEN DRONE
- Endpoint http://localhost:2023/api/v1/musalasoft/drone/battery-level
- Method - GET
- Param - serial (accepts serial as request parameter e.g http://localhost:2023/api/v1/musalasoft/drone/battery-level?serial=)
- Response - JSON

Returns battery level/capacity for a given drone with serial number in JSON format

---


### MEDICATION API Endpoint
- Endpoint http://localhost:2023/api/v1/musalasoft/medication/
- Method - POST
- Response - JSON

Each **Medication** has:
- name (allowed only letters, numbers, ‘-‘, ‘_’); DONE
- weight;
- code (allowed only upper case letters, underscore and numbers); DONE
- image (picture of the medication case).

 **NOTE** Medication might be loaded 
 if drone serial number is not found

 Using formdata in POSTMAN you to supply required keys and values

- **name** (Medication name)
- **weight** (Medication weight)
- **code** (Medication weight)
- **image** (Medication image)
- **droneSerial** (Drone serial number to be associated with the medication)


**RESPONSE RESULT**

```
{
    "data": {
        "medicationId": 1,
        "name": "Antibiotics-12",
        "weight": 20,
        "code": "COME_GH45",
        "image": "image.png",
        "droneSerial": "12345678"
    },
    "message": "Medication loaded for drone with serial 12345678",
    "status": 200
}
```


---

:scroll: **END**
