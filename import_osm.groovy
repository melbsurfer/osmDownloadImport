// TODO: Need to add the commands for the dropdb, createdb, psql, and osm2pgsql for each of the sub-regions.
// def cmdsAfrica = [
    //["dropdb", "-U", "postgres", "osm-Africa"],
    //["createdb", "-U", "postgres", "osm-Africa"],
    //["psql", "-U", "postgres", "-d", "osm-Africa", "-c", "create extension postgis"],
    //["osm2pgsql", "$osmImportItem" +  "africa-latest.osm.bz2", "--create", "-d", "osm-Africa", "-U", "postgres", "-P", "5432", "-S", "default.style"]
//]

// TODO: Add logging...

def databaseName = "groovyTestDB"

// TODO: Create the list here...
def osmImportList = ["liechtenstein"]

// TODO: Create the for...loop
for (osmImportItem in osmImportList){

    def cmds = [
        ["dropdb", "-U", "postgres", databaseName],
        ["createdb", "-U", "postgres", databaseName],
        ["psql", "-U", "postgres", "-d", databaseName, "-c", "create extension postgis"],
        ["osm2pgsql", "$osmImportItem" +  "-latest.osm.bz2", "--create", "-d", "$databaseName", "-U", "postgres", "-P", "5432", "-S", "default.style"]
    ]

    cmds.each { cmd ->
        println cmd
        def proc = cmd.execute()
        proc.consumeProcessOutput()
        println "\t${proc.waitFor()}"

    }

}


 