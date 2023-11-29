import java.util.Vector;
import java.util.

/**
 *@author Lilymoon Whalen
  Rachel Hu, Sophie Lin
 *@version
 */

 public class HollywoodGraph implements Graph<T>{
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

   
 }