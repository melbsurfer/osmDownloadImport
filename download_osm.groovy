/* 

Created: 12/17/2014
Modified: N/A

Summary: 

1. Downloads the latest Open Street Map data extracts from http://download.geofabrik.de
The following sub-regions are downloaded: Africa, Antarctica, Asia
Austraila and Oceania, Central America, North America, and South America. Europe is
downloaded by individual country because it is too large to load by itself.

Example: http://download.geofabrik.de/africa-latest.osm.bz2

2. Drops current OSM databases, recreates the OSM databases, and re-imports them via the downloaded data extract files

*/

import groovy.time.*

// TODO: Add parameter to download function that takes a sub directory so that we can
// change from downloading sub-regions to europe
def download( URL url, File file)
{
    url.withInputStream { input ->
        file.withOutputStream { output -> 
            output << input
        }
    }        
}

//http://download.geofabrik.de/europe/albania-latest.osm.bz2

// TODO: put in a try...catch to handle any errors.  E-mail upon error?
// http://kb.siteground.com/google_free_smtp_server/

// TODO: create this file if it doesn't exist
def f = new File("/tmp/osm/osm_download.txt")

// Test list:
//def osmSubRegionsLayerList = ["antarctica", "europe/albania", "europe/andorra", "europe/azores", "europe/cyprus", "europe/faroe-islands","europe/isle-of-man", "europe/kosovo", "europe/liechtenstein"]

// Real list:
//def osmSubRegionsLayerList = ["africa", "antarctica", "asia", "australia-oceania", "central-america", "north-america", "south-america", "europe/albania", "europe/andorra", "europe/austria", "europe/azores", "europe/belarus", "europe/belgium", "europe/bosnia-herzegovina", "europe/bulgaria", "europe/croatia", "europe/cyprus", "europe/czech-republic", "europe/denmark", "europe/estonia",  "europe/faroe-islands", "europe/finland", "europe/france", "europe/georgia",  "europe/germany", "europe/great-britain",  "europe/greece", "europe/hungary", "europe/iceland", "europe/ireland-and-northern-ireland", "europe/isle-of-man", "europe/italy", "europe/kosovo", "europe/latvia", "europe/liechtenstein", "europe/lithuania", "europe/luxembourg", "europe/macedonia", "europe/malta", "europe/moldova", "europe/monaco", "europe/montenegro", "europe/netherlands", "europe/norway", "europe/poland", "europe/portugal", "europe/romania", "europe/russia-european-part", "europe/serbia", "europe/slovakia", "europe/slovenia", "europe/spain", "europe/sweden", "europe/switzerland", "europe/turkey", "europe/ukraine"]

// Error make up list
def osmSubRegionsLayerList = ["europe"]


def currentStartTime = new Date()

f.append("\n--------------------------------------------------------------------------------------\n**Start** download of osm layers began on $currentStartTime")

for (osmLayer in osmSubRegionsLayerList){
    
    // Download URL Examples:
    // http://download.geofabrik.de/
    // http://download.geofabrik.de/europe/
    
    def fileUrl="http://download.geofabrik.de/" + "$osmLayer" + "-latest.osm.bz2"
    
    println "Downloading --> $osmLayer from: "
    println fileUrl.toURL()
    
    // logs the download process...
    f.append("\nDownloading --> " + "$osmLayer".toUpperCase())
    def currentLayerStartTime = new Date()
    f.append("\nStarted at: $currentLayerStartTime")
    f.append("\n" + fileUrl.toURL())
    
    // gets current osm layer
    //TODO: Change download directory location or have it set as a param
    download(fileUrl.toURL(), "/tmp/osm/$osmLayer-latest.osm.bz2" as File)
    
     def currentLayerEndTime = new Date()
     f.append("\nEnded at: $currentLayerEndTime")
     TimeDuration durationLayerRunTime = TimeCategory.minus(currentLayerEndTime, currentLayerStartTime)
     f.append("\nTotal run time: $durationLayerRunTime")
    
    // TODO: Add counter for list/array to exit loop instead of looking for file name? Use:  osmSubRegionsLayerList.size()
    if ("$osmLayer" == "europe/ukraine"){
        println ("Downloads complete.")
    } 

}

def currentEndTime = new Date()
TimeDuration durationRunTime = TimeCategory.minus(currentEndTime, currentStartTime)
println durationRunTime
f.append("\n**End** download of osm layers completed on $currentEndTime")
f.append("\nTotal run time: $durationRunTime")
f.append("\n--------------------------------------------------------------------------------------")


