import groovy.time.*

//def osmSubRegionImportList = ["africa", "antarctica", "asia", "australia-oceania", "central-america", "europe", "north-america", "south-america"]
def osmSubRegionImportList = ["central-america"]

// log process to file
def f = new File("osm_import.txt")

// TODO: Add Try...catch

def currentStartTime = new Date()
f.append("\n--------------------------------------------------------------------------------------\n**Start** import of osm layers began on $currentStartTime")

for (osmImportItem in osmSubRegionImportList){

    println "osm-$osmImportItem"

    // logs the import process...
    f.append("\nImporting --> " + "$osmImportItem".toUpperCase())

    def currentLayerStartTime = new Date()
    f.append("\nStarted at: $currentLayerStartTime")

    def cmds = [
        ["dropdb", "-U", "postgres", "omar-osm-$osmImportItem"],
        ["createdb", "-U", "postgres", "omar-osm-$osmImportItem"],
        ["psql", "-U", "postgres", "-d", "omar-osm-$osmImportItem", "-c", "create extension postgis"],
        ["osm2pgsql", "$osmImportItem-latest.osm.bz2", "-c", "-s", "-d", "omar-osm-$osmImportItem", "-U", "postgres", "-P", "5432", "-S", "default.style"]
    ]

    cmds.each { cmd ->
        println cmd
        def proc = cmd.execute()
        proc.consumeProcessOutput()
        println "\t${proc.waitFor()}"

    }
    
    def currentLayerEndTime = new Date()
    f.append("\nEnded at: $currentLayerEndTime")

    TimeDuration durationLayerRunTime = TimeCategory.minus(currentLayerEndTime, currentLayerStartTime)
    f.append("\nTotal run time: $durationLayerRunTime")

}

def currentEndTime = new Date()
TimeDuration durationRunTime = TimeCategory.minus(currentEndTime, currentStartTime)
println durationRunTime
f.append("\n**End** import of osm layers completed on $currentEndTime")
f.append("\nTotal run time: $durationRunTime")
f.append("\n--------------------------------------------------------------------------------------")
 