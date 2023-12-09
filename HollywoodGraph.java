
/**
 * Creates a graph representing the relationship between various movies and actors based on an input file, along with methods to analyze the graph
 * 
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version December 8, 2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import javafoundations.ArrayQueue;
import javafoundations.Graph;

public class HollywoodGraph implements Graph<FilmElement> {
    private Vector<FilmElement> vertices; // Vector to hold the vertices in the graph
    private Vector<LinkedList<FilmElement>> arcs; // Lists of adjacent vertices

    /**
     * Default constructor
     */
    public HollywoodGraph() {
        this.arcs = new Vector<LinkedList<FilmElement>>();
        this.vertices = new Vector<FilmElement>();
    }

    /**
     * Constructs a HollywoodGraph with data from a given source file
     * 
     * @param dataFileName file path of source file
     */
    public HollywoodGraph(String dataFileName) {
        this.arcs = new Vector<LinkedList<FilmElement>>();
        this.vertices = new Vector<FilmElement>();
        try {
            Scanner scan = new Scanner(new File(dataFileName));
            String line;
            String[] info; // stores the array created after line gets split

            scan.nextLine();// get rid of first line
            // fence post, brute force solve

            Movie movie;
            Actor actor;

            while (scan.hasNextLine()) {
                line = scan.nextLine();
                info = line.split(",");

                for (int i = 0; i < info.length; i++) {
                    info[i] = info[i].substring(1, info[i].length() - 1); // removes quotation marks
                }

                movie = new Movie(info[0]);
                actor = new Actor(info[1]);

                if (!filmElementAdded(movie)) {
                    this.addVertex(movie); // if movie isn'FilmElement in verticies already, make a new one
                } else {
                    for (int i = 0; i < vertices.size(); i++) {
                        if (vertices.elementAt(i).getName().equals(movie.getName())) {
                            movie = (Movie) vertices.elementAt(i); // if movie isn'FilmElement new, then make "movie"
                                                                   // variable point to the preexisting movie
                        }
                    }
                }
                if (!filmElementAdded(new Actor(info[1]))) {
                    actor = new Actor(info[1]);
                    this.addVertex(actor);
                } else {
                    for (int i = 0; i < vertices.size(); i++) {
                        if (vertices.elementAt(i).getName().equals(actor.getName())) {
                            actor = (Actor) vertices.elementAt(i); // if movie isn'FilmElement new, then make "movie"
                                                                   // variable point to the preexisting movie
                        }
                    }
                }

                actor.addRole(info[0], info[2], info[3], info[4], info[5]); // Movie name, character, type of role,
                                                                            // Billing, Gender
                movie.addActor(actor);
                this.addEdge(actor, movie);
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
     * @return total number of arcs in the graph
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
     * 
     * @param vertex1
     * @param vertex2
     * @return true if there is an arc between the two verticies
     *         false if there is not an arc
     *         false, and prints a warning, if a vertex is invalid
     */
    public boolean isArc(FilmElement vertex1, FilmElement vertex2) {
        int s = vertices.indexOf(vertex1);
        if (!this.isVertex(vertex1) || !this.isVertex(vertex2)) {
            System.out.println("Invalid vertex, vertex not found");
            return false;
        } else {
            LinkedList<FilmElement> temp = new LinkedList<FilmElement>();
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
     * 
     * @param FilmElement the vertex to be added to the graph
     */
    public void addVertex(FilmElement vertex) {
        if (!isVertex(vertex)) { // check if the vertex already exists
            this.vertices.add(vertex);
            this.arcs.add(new LinkedList<>());
        }
    }

    /**
     * Removes the input vertex from the graph.
     * If the input vertex does not belong in the graph, the graph is not changed.
     * Uses equals() for identidying the vertex to be removed.
     * 
     * @param FilmElement The vertex to be removed.
     */
    public void removeVertex(FilmElement vertex) {
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
     * Gets the FilmElement object stored in vertecies based on the name of a Movie
     * or Actor
     * 
     * @param String name - name of the Movie or Actor
     * @return FilmElement with the given name
     *         null when the movie could not be found
     */
    public FilmElement findVertex(String name) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.elementAt(i).getName().equals(name))
                return vertices.elementAt(i);
        }
        System.out.println("Cannot find the Actor or Movie in the graph");
        return null;
    }

    /**
     * Adds an arc between the two specified verticies.
     * No change and prints warning if a vertex isn't found.
     * 
     * @param vertex1
     * @param vertex2
     */
    public void addArc(FilmElement vertex1, FilmElement vertex2) {
        int s = vertices.indexOf(vertex1);
        if (!this.isVertex(vertex1) || !this.isVertex(vertex2)) {
            System.out.println("Vertex not found");
        } else {
            LinkedList<FilmElement> temp = new LinkedList<FilmElement>();
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
     * Does nothing and prints warning if one vertex isn't found.
     * 
     * @param vertex1
     * @param vertex2
     */
    public void removeArc(FilmElement vertex1, FilmElement vertex2) {
        int s = vertices.indexOf(vertex1);
        if (!this.isVertex(vertex1) || !this.isVertex(vertex2)) {
            System.out.println("Vertex not found");
        } else {
            LinkedList<FilmElement> temp = new LinkedList<FilmElement>();
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
     * 
     * @param vertex1
     * @param vertex2
     * @return true if there is an edge
     *         false if there is not an edge
     */
    public boolean isEdge(FilmElement vertex1, FilmElement vertex2) {
        return isArc(vertex1, vertex2) && isArc(vertex2, vertex1);
    }

    /**
     * Adds an edge between the two specified verticies
     * 
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(FilmElement vertex1, FilmElement vertex2) {
        this.addArc(vertex1, vertex2);
        this.addArc(vertex2, vertex1);
    }

    /**
     * Removes the edge between two specified vertecies
     * 
     * @param vertex1
     * @param vertex2
     */
    public void removeEdge(FilmElement vertex1, FilmElement vertex2) {
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
    public boolean isVertex(FilmElement vertex) {
        return (this.vertices.indexOf(vertex) != -1);
    }

    /**
     * Checks if the given FilmElement has been added to this.vertecies already
     * 
     * @param element
     * @return true if the element has been added
     *         false if the element has not been added
     */
    private boolean filmElementAdded(FilmElement element) {
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the current information into a tgf file
     * 
     * @param String tgf_file_name the name of the tgf file
     */
    public void saveTGF(String tgf_file_name) {
        try {
            PrintWriter writer = new PrintWriter(new File(tgf_file_name));
            // notice that indexing in the tgf format starts at 1 (not 0)

            // write vertices by iterating through vector "vertices"
            for (int i = 0; i < vertices.size(); i++) {
                writer.print((i + 1) + " " + vertices.get(i));
                writer.println("");
            }
            writer.println("#"); // # symbol separates the vertices from the arcs

            // write arcs by iterating through arcs vector
            for (int i = 0; i < arcs.size(); i++) { // for each adjacent list
                for (FilmElement destinationVertex : arcs.get(i)) { // for each destination vertex in that list
                    int destinationIndex = vertices.indexOf(destinationVertex); // find the index of that vertex
                    writer.println((i + 1) + " " + (destinationIndex + 1));
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("***ERROR***" + tgf_file_name + " could not be written");
        }
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return String a string representation of this graph
     */
    public String toString() {
        if (vertices.size() == 0)
            return "Graph is empty";

        String result = "Vertices: \n";
        result = result + vertices;

        result = result + "\n\nArcs: \n";
        for (int i = 0; i < vertices.size(); i++) {
            result = result + "from " + (vertices.get(i)).getName() + ": ";
            for (int j = 0; j < arcs.get(i).size(); j++) {
                result += arcs.get(i).get(j) + ", ";
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Returns an ArrayList of the names of every actor that has played in a given
     * movie
     * 
     * @param movie - Movie object to get the actors of
     *              null if the movie provided is not in the list of vertecies
     */
    public ArrayList<String> getAllActors(Movie movie) {
        ArrayList<String> list = new ArrayList<String>();
        int index = -1;
        if (isVertex(movie)) {
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.elementAt(i).getName().equals(movie.getName()))
                    index = i;
            }
            for (int i = 0; i < arcs.get(index).size(); i++) {
                list.add(arcs.get(index).get(i).getName());
            }
        } else {
            System.out.println("Invalid movie");
            return null;
        }
        return list;
    }

    /**
     * Calculates the number of movies separating one actor from another.
     * If they played in a movie together, the separation number is 0.
     * If one actor played in a movie with a third actor, and that third actor
     * played in a movie with the second actor,
     * then the first and second actor have a separation number of 1, so on, and so
     * forth.
     * 
     * @param a1 name of the first actor
     * @param a2 name of the second actor
     * @return separation number (-1 if there is no connection between the two
     *         actors)
     */
    public int separation(String a1, String a2) {
        int level = -1;
        // create array to store if an element has been visisted. the index corresponds with the index of the element in this.vertecies, then mark all as unvisited
        boolean[] visited = new boolean[this.vertices.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        ArrayQueue<FilmElement> queue = new ArrayQueue<FilmElement>(); // queue for BFS
        FilmElement firstInLevel; // keep track of the first FilmElement added to the queue in a given level

        boolean found = false; // keep track of if a2 has been found from a1 yet

        Actor actor1 = new Actor(a1);
        Actor actor2 = new Actor(a2);

        // get the Actor object in this.vertecies that corresponds to the actor
        // specified in a1
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.elementAt(i).equals(actor1)) {
                actor1 = (Actor) vertices.elementAt(i);
            }
            // same as above, just for a2
            if (vertices.elementAt(i).equals(actor2)) {
                actor2 = (Actor) vertices.elementAt(i);
            }
        }

        int a1Index = this.vertices.indexOf(actor1);

        firstInLevel = actor1;
        queue.enqueue(actor1); // enqueue a1 to the queue
        visited[a1Index] = true;

        boolean keepGoing = true;

        while (!queue.isEmpty() && keepGoing) {
            FilmElement current = queue.dequeue();
            int currentIndex = vertices.indexOf(current);
            
            if (current.equals(firstInLevel)) {
                level++;
                LinkedList<FilmElement> currentArcsList = arcs.elementAt(currentIndex);
                if (currentArcsList.size() != 0) { // if there's actually more nodes connected to it
                    for (int i = 0; i < currentArcsList.size(); i++) {
                        if (!visited[vertices.indexOf(currentArcsList.get(i))]) { // if it hasn't been visited yet
                            firstInLevel = currentArcsList.get(i);
                            queue.enqueue(firstInLevel);
                            visited[vertices.indexOf(firstInLevel)] = true;
                            break;
                        }
                    }
                }
            }
            if (current.equals(actor2)) {
                found = true;
                break;
            }
            
            for (int index = 0; index < this.getNumVertices(); index++) { // TODO more efficient if search through arcs?
                if (isArc(current, vertices.elementAt(index)) && !visited[index]) { // for every index that hasn't been visited yet and if it has an arc with next
                    FilmElement adjcacent = vertices.elementAt(index);

                    //check if there's any firstInLevels left in the queue
                    ArrayQueue<FilmElement> tempQueue = new ArrayQueue<FilmElement>();
                    int queueSizeHolder = queue.size();
                    FilmElement tempFilmElement;
                    boolean hasMoreFirsts = false;
                    for (int rep = 0; rep < queueSizeHolder; rep ++) {
                        tempFilmElement = queue.dequeue();
                        if (tempFilmElement.equals(firstInLevel)) {
                            hasMoreFirsts = true;
                        }
                        queue.enqueue(tempFilmElement);
                    }

                    /*
                     * if current isn't the firstInLevel, 
                     * but there's no firstInLevel's in the queue behind it, 
                     * then the next thing added is the firstInLevel for the next level
                     */
                    if (!current.equals(firstInLevel) && !hasMoreFirsts) { 
                        firstInLevel = adjcacent;
                    }
                    queue.enqueue(adjcacent);
                    visited[index] = true;
                }
            }
        }

        if (found != true) {
            return -1; // no connection
        }
        return level / 2;
    }

    /**
     * Bechdel Uphold test, tests if a cast for a movie is at least 50% women
     * 
     * @param movie - movie to analyze
     */
    public double upholdTest(Movie movie) {
        ArrayList<Actor> actors = movie.getActorList();
        int countF = 0;
        for (int i = 0; i < actors.size(); i++) {
            String gender = actors.get(i).genderAssociatedWithMovie(movie.getName());
            if (gender.equalsIgnoreCase("FEMALE")) {
                countF++;
            }
        }
        return (double) countF / actors.size();
    }

    /**
     * //TODO NEED DOCUMENTATION
     * @param fileName
     */
    public void saveUpholdTest(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));
            for (int i = 0; i < vertices.size(); i++) {
                FilmElement temp = vertices.get(i);
                if (temp.getType().equals("Movie")) {
                    writer.print(vertices.get(i));
                    double test = upholdTest((Movie) temp);
                    double percentage = (double) Math.round(test * 10000) / 100;
                    if (test >= .50) {
                        writer.print(": Cast is " + percentage + "% female PASSES the Uphold Test");
                    } else {
                        writer.print(": Cast is " + percentage + "% female FAILS the Uphold Test");
                    }
                    writer.println();
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("***ERROR***" + fileName + " could not be written");
        }
    }

    public static void main(String[] args) {
        HollywoodGraph s1 = new HollywoodGraph("data/nextBechdel_castGender.txt");
        System.out.println(s1);
        s1.saveTGF("test1.tgf");
        s1.saveUpholdTest("bechdelProject_testing.txt");

        System.out.println("testing separation: expected 1");
        System.out.println(s1.separation(//replace with actors to test from large data set bc my computer is stoopid));

        System.out.println("Testing getAllActors()");
        ArrayList<String> a1 = s1.getAllActors((Movie) s1.findVertex("The Jungle Book"));
        for (String n : a1) {
            System.out.print(n + ", ");
        }
    }
}