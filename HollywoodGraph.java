
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
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import javafoundations.Graph;

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
    /**
     * Default constructor
     */
    public HollywoodGraph() {
        this.arcs = new Vector<LinkedList<T>>();
        this.vertices = new Vector<T>();
    }

    public HollywoodGraph(String dataFileName) {
        try {
            Scanner scan = new Scanner(new File(dataFileName));
            String line;
            String[] info; // stores the array created after line gets split
            scan.nextLine();// get rid of first line

            Movie movie;

            while (scan.hasNextLine()) {
                line = scan.nextLine();
                info = line.split(",");


                movie = new Movie(info[0]);
                this.addVertex(null);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name");
        }
    }

    public void generateTGF() {

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
        for(int i = 0; i < arcs.size(); i++){
            total+= arcs.get(i).size(); 
        }

        return total;
    }

    public boolean isArc(T vertex1, T vertex2) {
        int s = vertices.indexOf(vertex1);
        if(vertices.indexOf(vertex1)< 0 || vertices.indexOf(vertex2) < 0 ){
            return false;
        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            for (int i = 0; i < temp.size(); i++){
                if (temp.get(i).equals(vertex2)){
                    return true;                  
                }
            }
            return false;

        }  
    }

    @Override
    public boolean isEdge(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEdge'");
    }

    @Override
    public void addVertex(T vertex) {
        throw new UnsupportedOperationException("Unimplemented method 'addVertex'");
    }

    @Override
    public void removeVertex(T vertex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeVertex'");
    }

    @Override
    public void addArc(T vertex1, T vertex2) {
        int s = vertices.indexOf(vertex1);
        if(vertices.indexOf(vertex1)< 0 || vertices.indexOf(vertex2) < 0 ){

        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            boolean shouldAdd = true;
            for (int i = 0; i < temp.size(); i++){
                if (temp.get(i).equals(vertex2)){
                    shouldAdd = false;                    
                }

            }
            if(shouldAdd){
                arcs.get(s).add(vertex2);
            }
        }
    }

    public void removeArc(T vertex1, T vertex2) {
        int s=vertices.indexOf(vertex1);
        if(vertices.indexOf(vertex1)< 0 || vertices.indexOf(vertex2) < 0 ){
        } else {
            LinkedList<T> temp = new LinkedList<T>();
            temp = arcs.get(s);
            for (int i = 0; i < temp.size(); i++){
                if (temp.get(i).equals(vertex2)){
                    temp.remove(s);
                }
            }

        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEdge'");
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEdge'");
    }

    @Override
    public void saveTGF(String tgf_file_name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveTGF'");
    }

}