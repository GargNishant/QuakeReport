# QuakeReport
App which Extracts data about the earthquakes around the world in the last 30 days with entry limit of 90 (can be changed) and minimum magnitude of 4.0 (changable).

## About

USGS is a website which can be used to view the Earthquake data, their timings , location around the globe.
This Data can be extracted using the WebService provided by the Website in various formats like JSON, and XML
which are widely used.

The main purpose of the app is to extract the most recent data about the earthquake from the USGS Website
and show it in an Android Application. The time frame for most recent is 30 days, means all the earthquake
in the last 30days will be displayed.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes and learning purpose. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Things you need to install the software

```
Android Studio
Emulator or a Physical Device
```

## Deployment

To test the app in any Physical Device or Emulater follow the Installation Process mentioned Below.

### Installing

A step by step series of examples that tell you have to get a development env running

1. Download Android Studio from the Following Link
[Android Studio](https://developer.android.com/studio/index.html)

2. Install using the Steps as mentioned during the installation process

3. Download this Repository by clicking on "Clone or Download Option" and use ZIP

4. Extract the package to a Desired Location and open the project in Android Studio

5. Compile and Run

6. Make Sure you are connected to the Internet so that it can fetch the Data From the Servers

## Version

Latest Version: 3
## Authors
* **Udacity Nanodegree Program**
* **Nishant Garg**  - [Nishant Garg](https://github.com/GargNishant)

## Master Branch:          
Simple App which does not uses any internet Connection. The JSON response is Hardcoded and the app parses the String and displays the Data from that Json Response. Uses List View and Custom Adapters

## V1 Branch:    
Upgraded the app so that it can extract the data from the USGS web Site and parse it. HTTPS Clients are used for networking purpose in this branch. List Views and custom adapter are being used in this branch so that it can 

## V2 Branch:
Upgraded the application. Now it uses the all new Volley for handling the network Work making the code much easier to code and to read. Recycler view also replaced the old List View for much better and smooth experince.

