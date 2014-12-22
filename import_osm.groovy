def cmdDeleteDb = ["dropdb", "omar_base"]
//def cmdAddDb = ["createdb", "osm-auto-merc"]
def procDeleteDb = cmdDeleteDb.execute()
procDeleteDb.waitFor()
//def procAddDb = cmdAddDb.execute()
//procAddDb.waitFor()

// TODO: Need to be able to add PostGIS extension to the newly created
// databases or be loop through all of the DB's and drop the osm
// tables

//println "return code: ${procAddPostgisExt.exitValue()}"
//println "stderr: ${procAddPostgisExt.err.text}"
//println "stdout: ${procAddPostgisExt.in.text}"

// More down here...
println "return code: ${ procDeleteDb.exitValue()}"
println "stderr: ${procDeleteDb.err.text}"
println "stdout: ${procDeleteDb.in.text}" // *out* from the external program is *in* for groovy

//println "return code: ${ procAddDb.exitValue()}"
//println "stderr: ${procAddDb.err.text}"
//println "stdout: ${procAddDb.in.text}"

//def osmImportLayerList = ["azores", "cyprus"]

//for (osmImportLayer in osmImportLayerList){

    //println "Importing --> $osmImportLayer"
    
    //def command = ["osm2pgsql", "$osmImportLayer-latest.osm.bz2", "--append", "--unlogged", "-merc", "-d", "osm-auto-merc", "-U", "postgres", "-P", "5432", "-S", "default.style"]
    //def proc = command.execute()
    //proc.waitFor()  
    
    // Obtain status and output
    //println "return code: ${ proc.exitValue()}"
    //println "stderr: ${proc.err.text}"
    //println "stdout: ${proc.in.text}"

//}
 