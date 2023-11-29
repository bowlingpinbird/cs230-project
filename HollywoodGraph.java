import java.util.Vector;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
/**
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version 11/28/2023
 */

public class HollywoodGraph<T> implements Graph<T>{
    private Vector<T> vertices; //Vector to hold the vertices in the graph
    private Vector<LinkedList<T>> arcs;   // Lists of adjacent vertices

    public HollywoodGraph(){
        this.arcs = new Vector<LinkedList<T>>();
        this.vertices = new Vector<T>();
    }

    /**
     * Creates and returns a new graph of Strings using the data found
     * in the input tgf file.
     * If the file does not exist, a message is printed.
     *
     * @param String The name of the tgf file to read the graph from
     * @return AdjListsDiGraph<String> the graph of Strings created
     */
    public static HollywoodGraph<String> HollywoodGraph(String tgf_fName) {
        //create an empty graph of Strings
        HollywoodGraph<String> g = new HollywoodGraph<String>();

        try{
            Scanner scanner = new Scanner(new File(tgf_fName)); 

            //read vertices
            while (!scanner.next().equals("#")){ //discard special symbol (#)
                String token = scanner.next(); //read next String
                g.addVertex(token); //add it a vertex to the graph
            }

            //read arcs
            while (scanner.hasNext()){
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                g.addArc(from-1, to-1); //uses helper method
            }
            scanner.close();
        } catch (IOException ex) {
            System.out.println(" ***ERROR***  " +  tgf_fName + " file could not be read. ");
        }
        return g;
    } 

    /**
     * Saves the current graph into a .tgf file.
     * @param the name of the file to write to
     */
    public void saveToTGF(String tgf_file_name) {
        try {
            PrintWriter writer = new PrintWriter(new File(tgf_file_name));
            //notice that indexing in the tgf format starts at 1 (not 0)

            //write vertices by iterating through vector "vertices"
            for (int i = 0; i < vertices.size(); i++) {
                writer.print((i+1) + " " + vertices.get(i));
                writer.println("");
            }
            writer.println("#"); // # symbol separates the vertices from the arcs

            //write arcs by iterating through arcs vector
            for (int i = 0; i < arcs.size(); i++){ //for each adjacent list
                for (T destinationVertex :arcs.get(i)) { //for each destination vertex in that list
                    int destinationIndex = vertices.indexOf(destinationVertex); //find the index of that vertex
                    writer.println((i+1) + " " + (destinationIndex+1));
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("***ERROR***" +  tgf_file_name + " could not be written");
        }
    }     

    /**
     * Helper. Inserts an arc between two vertices of the graph.
     *
     * @param int The index of the first vertex in the vertices vector (starting from 0)
     * @param int The index of the second vertex in the vertices vector (starting from 0)
     */
    private void addArc (int index1, int index2) {
        T source = vertices.get(index1);
        T destination = vertices.get(index2);
        addArc(source, destination);
    }

    /**
     *  Returns a string representation of the graph.
     *
     *  @return String a string representation of this graph
     */
    public String toString() {
        if (vertices.size() == 0) return "Graph is empty";

        String result = "Vertices: \n";
        result = result + vertices;

        result = result + "\n\nArcs: \n";
        for (int i=0; i< vertices.size(); i++)
            result = result + "from " + vertices.get(i) + ": "  + arcs.get(i) + "\n";

        return result;
    }
    // END OF GIVEN

    // START OF LAB
    /**
     * Returns true if the graph is empty and false otherwise.
     * @return boolean True iff the graph is empty
     * 
     */
    public boolean isEmpty() {

        return vertices.isEmpty();       
    }

    /**
     * Returns the number of vertices in the graph
     * @return int The number of vertices in the graph
     * 
     */
    public int getNumVertices() {
        return vertices.size();
    }

    /**
     * Returns the number of arcs in the graph
     * @return int The number of arcs in the graph
     * 
     */
    public int getNumArcs() {
        int total = 0;
        for(int i = 0; i < arcs.size(); i++){
            total+= arcs.get(i).size(); 
        }

        return total;
    }

    /**
     * Adds the input vertex to the graph.
     * If the vertex already exists in the graph, the graph is not changed.
     * @param T the vertex to be added to the graph
     * 
     */
    public void addVertex (T vertex) {
        if(vertices.indexOf(vertex)< 0){
            vertices.add(vertex);
            arcs.add(new LinkedList<>());
        }
    }

    /**
     * Adds an arc to the graph, from source to destination.
     * If source or destination do not exist in the graph,
     * the graph is not changed.
     * Verifies that source and destination are valid vertices in the graph,
     * and that the newly added arc does not already belong in the graph.
     *
     * @param T the source of the arc
     * @param T the destination of the arc
     * 
     */
    public void addArc (T source, T destination){
        int s = vertices.indexOf(source);
        if(vertices.indexOf(source)< 0 || vertices.indexOf(destination) < 0 ){

        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            boolean shouldAdd = true;
            for (int i = 0; i < temp.size(); i++){
                if (temp.get(i).equals(destination)){
                    shouldAdd = false;                    
                }

            }
            if(shouldAdd){
                arcs.get(s).add(destination);
            }
        }
    }
    // END OF LAB

    // START OF ASSIGNMENT

    /**
     * Returns true iff a directed connection exists between the two input vertices
     * @param T the first vertex
     * @param T the second vertex
     * @return boolean true iff a directed connection
     * exists from the first vertex to the second
     */
    public boolean isArc (T v1, T v2){
        int s = vertices.indexOf(v1);
        if(vertices.indexOf(v1)< 0 || vertices.indexOf(v2) < 0 ){
            return false;
        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            for (int i = 0; i < temp.size(); i++){
                if (temp.get(i).equals(v2)){
                    return true;                  
                }
            }
            return false;

        }  
    }

    /**
     * Removes the input vertex from the graph.
     * If the input vertex does not belong in the graph, the graph is not changed.
     * Uses equals() for identidying the vertex to be removed.
     * @param T The vertex to be removed.
     */
    public void removeVertex (T vertex) {
        int v = vertices.indexOf(vertex);
        if(v < 0){ // the vertex does not exist
        } else{
            vertices.remove(v);
            arcs.remove(v);
            for(int i = 0; i < arcs.size(); i++){
                LinkedList<T> temp = new LinkedList<T>();
                temp = arcs.get(i);
                for (int j = 0; j < temp.size(); j++){
                    if (temp.get(j).equals(vertex)){
                        temp.remove(j);
                    }
                }
            }
        }
    }

    /**
     * Removes the arc between v1 and v2.
     * If v1 or v2, or the arc from v1 to v2 does not exist,
     * the graph does not change.
     *
     * @param T the source of the arc to be removed
     * @param T the destination of the arc to be removed
     */
    public void removeArc (T v1, T v2) {
        int s=vertices.indexOf(v1);
        if(vertices.indexOf(v1)< 0 || vertices.indexOf(v2) < 0 ){
        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            for (int i = 0; i < temp.size(); i++){
                if (temp.get(i).equals(v2)){
                    temp.remove(s);
                }
            }

        }

    }

}