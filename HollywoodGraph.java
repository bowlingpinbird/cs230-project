
/**
 * Creates a graph representing the relationship between various omvies and actors based on an input file, along with methods to analyze the graph
 * 
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version December 6, 2023
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

            Movie movie; // temporary variables to hold data
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
                    this.addVertex(movie); // if movie isn't in verticies already, make a new one
                } else {
                    for (int i = 0; i < vertices.size(); i++) {
                        if (vertices.elementAt(i).equals(movie)) {
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
                        if (vertices.elementAt(i).equals(actor)) {
                            actor = (Actor) vertices.elementAt(i); // if movie isn'FilmElement new, then make "actor"
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
     */
    public boolean isArc(FilmElement vertex1, FilmElement vertex2) {
        int s = vertices.indexOf(vertex1);
        if (vertices.indexOf(vertex1) < 0 || vertices.indexOf(vertex2) < 0) {
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
     * If the input vertex does not belong in the graph, the graph is not changed,
     * and a warning is printed.
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
     * Finds the index of the given movie name
     * 
     * @param String movie
     * @return FilmElement with the given name
     *         null when the movie could not be found
     */
    public FilmElement findVertex(String movie) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.elementAt(i).getName().equals(movie))
                return vertices.elementAt(i);
        }
        System.out.println("Cannot find the movie in the graph");
        return null;
    }

    /**
     * Adds an arc between the two specified verticies
     * 
     * @param vertex1
     * @param vertex2
     */
    public void addArc(FilmElement vertex1, FilmElement vertex2) {
        int s = vertices.indexOf(vertex1);
        if (vertices.indexOf(vertex1) > 0 && vertices.indexOf(vertex2) > 0) {
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
     * 
     * @param vertex1
     * @param vertex2
     */
    public void removeArc(FilmElement vertex1, FilmElement vertex2) {
        int s = vertices.indexOf(vertex1);
        if (vertices.indexOf(vertex1) < 0 || vertices.indexOf(vertex2) < 0) {
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
     */
    public ArrayList<String> getAllActors(Movie movie) {
        ArrayList<String> list = new ArrayList<String>();
        int index = -1;
        if (isVertex(movie)) {
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.elementAt(i).getName().equals(movie.getName()))
                    index = i;
            }
        }
        for (int i = 0; i < arcs.get(index).size(); i++) {
            list.add(arcs.get(index).get(i).getName());
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
     * Completed as a variation of BFS that keeps track of the depth from the
     * starting node as it searches
     * 
     * @param a1 name of the first actor
     * @param a2 name of the second actor
     * @return separation number (-1 if there is no connection between the two
     *         actors)
     */
    public int separation(String a1, String a2) {
        int level = -1; // keeps track of the level of depth from the starting node

        // create array to store if an element has been visisted. the index corresponds
        //
        // with the index of the element in this.vertecies, then mark all as unvisited
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
        for (int rep = 0; rep < vertices.size(); rep++) { // O(n)
            if (vertices.elementAt(rep).equals(actor1)) {
                actor1 = (Actor) vertices.elementAt(rep);
            }
            // same as above, just for a2
            if (vertices.elementAt(rep).equals(actor2)) {
                actor2 = (Actor) vertices.elementAt(rep);
            }
        }

        int a1Index = this.vertices.indexOf(actor1);
        firstInLevel = actor1;
        queue.enqueue(actor1); // enqueue a1 to the queue
        visited[a1Index] = true;

        boolean keepGoing = true;

        while (!queue.isEmpty() && keepGoing) { // O(n)
            FilmElement current = queue.dequeue();
            int currentIndex = vertices.indexOf(current);


            if (current.equals(firstInLevel)) {
                level++;
                if (current.equals(actor2)) { // check if this is the vertex we want for actor2
                    found = true;
                    keepGoing = false;
                    break;
                }
                LinkedList<FilmElement> currentArcsList = arcs.elementAt(currentIndex);
                for (int rep = 0; rep < currentArcsList.size(); rep++) { // loop through the rest of the nodes
                    if (!visited[vertices.indexOf(currentArcsList.get(rep))]) { // if it hasn't been visited yet
                        firstInLevel = currentArcsList.get(rep);
                        queue.enqueue(firstInLevel);
                        visited[vertices.indexOf(firstInLevel)] = true;
                        break;
                    }
                }
                queue.enqueue(firstInLevel);
            }

            // check if there's any firstInLevels left in the queue
            int queueSizeHolder = queue.size();
            FilmElement tempFilmElement;
            boolean hasMoreFirsts = false;
            for (int rep = 0; rep < queueSizeHolder; rep++) { // O(n)
                tempFilmElement = queue.dequeue();
                if (tempFilmElement.equals(firstInLevel)) {
                    hasMoreFirsts = true;
                }
                queue.enqueue(tempFilmElement);
            }

            for (int index = 0; index < this.getNumVertices(); index++) { // TODO more efficient if search through arcs?
                                                                          // //O(n)
                if (isArc(current, vertices.elementAt(index)) && !visited[index]) { // for every index that hasn't been
                                                                                    // visited yet and if it has an arc
                                                                                    // with next
                    FilmElement adjcacent = vertices.elementAt(index);
                    // if current isn't the firstInLevel, but there's no firstInLevel's in the queue
                    // behind it, then the next thing added is the firstInLevel for the next level
                    if (!current.equals(firstInLevel) && !hasMoreFirsts) {
                        firstInLevel = adjcacent;
                        hasMoreFirsts = true;
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
     * Writes all actors to an output file. Displays an
     * error message if the output file cannot be created.
     * 
     * @param ArrayList<String> list of movies/actors to be printed out
     * @param outFileName       the file name to be outputted
     * @param String            name of the actor/movie
     * 
     */
    public static void writeListToFile(ArrayList<String> a1, String outFileName, String name) {
        try {
            PrintWriter writer = new PrintWriter(outFileName);
            writer.println("Finding all movies/actors for " + name + ":");
            for (String n : a1)
                writer.print(n + ", ");
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex); // Handle file-not-found
        }
    }

    /**
     * Bechdel Uphold test, tests if a cast for a movie is at least 50% women
     * 
     * @param args
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
     * Saves results from the Uphold test to a specfied .txt file
     * 
     * @param fileName - path of the file to save to
     */
    public void saveUpholdTest(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));
            for (int i = 0; i < vertices.size(); i++) {
                FilmElement temp = vertices.get(i);
                if (temp.getType().equals("Movie")) {
                    writer.print(vertices.get(i));
                    double test = upholdTest((Movie) temp);
                    double percentage = (double) Math.round(test * 10000) / 100; // writes women in cast in percentage
                                                                                 // of cast
                    if (test >= .50) { // if there is more or equal to fifty percent of women then it pases the test
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

        s1.saveUpholdTest("bechdelProject_testing.txt"); // writes result of Bechdel test to this file
        System.out.println("Testing getAllActors()");
        ArrayList<String> a1 = s1.getAllActors(s1.findVertex("The Jungle Book"));
        try {
            PrintWriter writer = new PrintWriter("bechdelProject_testing.txt");
            writer.println("Finding all actors for " + "The Jungle Book" + ":");
            for (String n : a1)
                writer.print(n + ", ");

            System.out.println("Testing getAllMovies()");
            ArrayList<String> a2 = s1.getAllMovies(s1.findVertex("Jennifer Lawrence"));
            writer.println();
            writer.println("Finding all movies for " + "Jennifer Lawrence" + ":");
            for (String n : a2)
                writer.print(n + ", ");

            // writing results to file
            for (String n : a2)
                a1.add(n);
            HollywoodGraph.writeListToFile(a1, "bechdelProject_testing.txt", "Jennifer Lawrence");

            writer.println("testing separation()");
            writer.println("Megan Fox to Tyler Perry. Expected: 1. Actual: " + s1.separation("Megan Fox", "Tyler Perry"));
            writer.println("Nick Arapoglou to Tyler Perry. Expected: 3. Actual: " + s1.separation("Nick Arapoglou", "Tyler Perry"));

            System.out.println("Testing Bechdel test");
            // s1.saveUpholdTest("bechdelProject_testing.txt");
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex); // Handle file-not-found
        }
        ArrayList<String> a1 = s1.getAllActors((Movie) s1.findVertex("The Jungle Book"));
        for (String n : a1) {
            System.out.print(n + ", ");
        }
    }
}