package de.tu_clausthal.in.mec.modeling.model.graph.jung;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.IGraph;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import edu.uci.ics.jung.graph.Graph;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;


/**
 * abstract graph class
 * @tparam N node type
 * @tparam E edge type
 */
public abstract class IBaseGraph<N extends INode, E extends IEdge> implements IGraph<N, E>
{
    /**
     * graph instance
     */
    private final Graph<N, E> m_graph;
    /**
     * id of the graph
     */
    private final String m_id;
    /**
     * map with nodes
     */
    private final Map<String, INode> m_nodes = new ConcurrentHashMap<>();
    /**
     * map with edges
     */
    private final Map<String, IEdge> m_edges = new ConcurrentHashMap<>();

    /**
     * ctor
     *
     * @param p_id identifier / name of the graph
     * @param p_graph graph instance
     */
    protected IBaseGraph( @NonNull final String p_id, @NonNull final Graph<N, E> p_graph )
    {
        m_id = p_id;
        m_graph = p_graph;
    }

    @Override
    public final int hashCode()
    {
        return m_id.hashCode();
    }

    @Override
    public final boolean equals( final Object p_object )
    {
        return p_object instanceof IGraph<?, ?> && p_object.hashCode() == this.hashCode();
    }

    @NonNull
    @Override
    public final String id()
    {
        return m_id;
    }

    @NonNull
    @Override
    public final Stream<? extends E> edges()
    {
        return m_graph.getEdges().stream();
    }

    @NonNull
    @Override
    public final Stream<? extends N> nodes()
    {
        return m_graph.getVertices().stream();
    }

    @Override
    public boolean containsnode( @NonNull final String p_id )
    {
        return false;
    }

    @Override
    public boolean containsedge( @NonNull final String p_id )
    {
        return m_edges.containsKey( p_id );
    }

    @Override
    public final E edge( @NonNull final String p_id )
    {
        return m_edges.get( p_id ).raw();
    }

    @Override
    public final N node( @NonNull final String p_id )
    {
        return m_nodes.get( p_id ).raw();
    }

    @Override
    public IGraph<N, E> add( @NonNull final N p_start, @NonNull final N p_end, @NonNull final E p_edge )
    {
        final N l_start = m_nodes.putIfAbsent( p_start.id(), p_start ).raw();
        final N l_end = m_nodes.putIfAbsent( p_end.id(), p_end ).raw();

        m_graph.addEdge( m_edges.putIfAbsent( p_edge.id(), p_edge ).raw(), l_start, l_end );
        return this;
    }
}
