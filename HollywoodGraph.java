/**
 * Creates a graph representing the relationship between various omvies and actors based on an input file
 * 
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version November 28, 2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import javafoundations.Graph;

public class HollywoodGraph<T> implements Graph<T> {
    private Vector<T> vertices; // Vector to hold the vertices in the graph
    private Vector<LinkedList<T>> arcs; // Lists of adjacent vertices

    /**
     * Default constructor
     */
    public HollywoodGraph() {
        this.arcs = new Vector<LinkedList<T>>();
        this.vertices = new Vector<T>();
    }

    /**
     * Constructs a HollywoodGraph with data from a given source file
     * @param dataFileName file path of source file
     */
    public HollywoodGraph(String dataFileName) {
        this.arcs = new Vector<LinkedList<T>>();
        this.vertices = new Vector<T>();
        try {
            Scanner scan = new Scanner(new File(dataFileName));
            String line;
            String[] info; // stores the array created after line gets split
        
            scan.nextLine();// get rid of first line

            Movie movie;
            Actor actor;

              while (scan.hasNextLine()) {
                line = scan.nextLine();                
                info = line.split(",");
                 for(int i = 0; i < info.length;i++){
                    info[0] = info[0].substring(1,info[0].length()-1); // removes quotation marks
                 }
                actor = new Actor(info[1]);
                actor.addRole(info[0], info[2], info[3], info[4], info[5]); // Movie name, character, type of role, Billing, Gender
                if(!isVertex((T)actor)){
                    this.addVertex((T)actor);
                } 
                movie = new Movie(info[0]);
                if(!isVertex((T)movie)){
                    this.addVertex((T)movie);
                }
                this.addEdge((T)actor, (T)movie);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name");
        }
    }

    /**
     * Determines if this graph is empty or not based on the number of vertices it
     * has
     * 
     * @return true if the graph is empty
     *         false if not
     */
    public boolean isEmpty() {
        return this.vertices.size() == 0;
    }

    /**
     * Returns the number of vertices (movies and actors) in the graph
     * 
     * @return int number of vertices
     */
    public int getNumVertices() {
        return vertices.size();
    }

    /**
     * Returns the number of arcs (relationships between movies and actors in the
     * graph)
     * 
     * @return int
     */
    public int getNumArcs() {
        int total = 0;
        for (int i = 0; i < arcs.size(); i++) {
            total += arcs.get(i).size();
        }
        return total;
    }

    /**
     * Checks whether there is an arc between two given verticies
     * @param vertex1
     * @param vertex2
     * @return
     */
    public boolean isArc(T vertex1, T vertex2) {
        int s = vertices.indexOf(vertex1);
        if (vertices.indexOf(vertex1) < 0 || vertices.indexOf(vertex2) < 0) {
            return false;
        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).equals(vertex2)) {
                    return true;
                }
            }
            return false;

        }
    }

    /**
     * Adds the input vertex to the graph.
     * If the vertex already exists in the graph, the graph is not changed.
     * @param T the vertex to be added to the graph
     */
    public void addVertex (T vertex) {
        if (!isVertex(vertex)) { //check if the vertex already exists
            this.vertices.add(vertex);
            this.arcs.add(new LinkedList<>());
        }
    }

    /**
     * Removes the input vertex from the graph.
     * If the input vertex does not belong in the graph, the graph is not changed.
     * Uses equals() for identidying the vertex to be removed.
     * 
     * @param T The vertex to be removed.
     */
    public void removeVertex(T vertex) {
        if (isVertex(vertex)) { // if the given vertex actually exists
            // remove arcs pointing to the vertex
            for (int vert = 0; vert < this.arcs.size(); vert++) { // iterate through arcs first layer, representing
                // verticies
                int keepNumArcs = this.arcs.elementAt(vert).size();
                for (int arc = 0; arc < keepNumArcs; arc++) { // iterate through each vertex's linked list of arcs
                    if (this.arcs.elementAt(vert).get(arc).equals(vertex)) {
                        removeArc(this.vertices.elementAt(vert), vertex);
                    }
                }
            }
            // remove the vertex
            int targetVertIndex = this.vertices.indexOf(vertex);
            this.vertices.remove(vertex);
            this.arcs.remove(targetVertIndex);
        }
    }

    /**
     * Adds an arc between the two specified verticies
     * @param vertex1
     * @param vertex2
     */
    public void addArc(T vertex1, T vertex2) {
        int s = vertices.indexOf(vertex1);
        if (vertices.indexOf(vertex1) < 0 || vertices.indexOf(vertex2) < 0) {

        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            boolean shouldAdd = true;
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).equals(vertex2)) {
                    shouldAdd = false;
                }

            }
            if (shouldAdd) {
                arcs.get(s).add(vertex2);
            }
        }
    }

    /**
     * Removes the arc between the two specified verticies
     * @param vertex1
     * @param vertex2
     */
    public void removeArc(T vertex1, T vertex2) {
        int s = vertices.indexOf(vertex1);
        if (vertices.indexOf(vertex1) < 0 || vertices.indexOf(vertex2) < 0) {
        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).equals(vertex2)) {
                    temp.remove(s);
                }
            }

        }
    }

    /**
     * Checks whether there's an edge between the two specified verticies
     * @param vertex1
     * @param vertex2
     * @return true if there is an edge
     *         false if there is not an edge
     */
    public boolean isEdge(T vertex1, T vertex2) {
        return isArc(vertex1, vertex2) && isArc(vertex2, vertex1);
    }

    /**
     * Adds an edge between the two specified verticies
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(T vertex1, T vertex2) {
        this.addArc(vertex1, vertex2);
        this.addArc(vertex2, vertex1);
    }

    /**
     * Removes the edge between two specified vertecies
     * @param vertex1
     * @param vertex2
     */
    public void removeEdge(T vertex1, T vertex2) {
        this.removeArc(vertex1, vertex2);
        this.removeArc(vertex2, vertex1);
    }

    /**
     * Checks if the given vertex exists in this graph
     * 
     * @param vertex to find
     * @return true if the vertex exists
     *         false if not
     */
    public boolean isVertex(T vertex) {
        return (this.vertices.indexOf(vertex) != -1);
    }
    /**
     * Saves the current information into a tgf file
     * @param String tgf_file_name the name of the tgf file
     */
    public void saveTGF(String tgf_file_name) {
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

    public static void main(String[] args){
        HollywoodGraph<String> s1 = new HollywoodGraph<String>("data/nextBechdel_castGender.txt");
        System.out.println(s1);
    }

}
