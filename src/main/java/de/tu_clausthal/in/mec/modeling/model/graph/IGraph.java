package de.tu_clausthal.in.mec.modeling.model.graph;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.stream.Stream;


public interface IGraph<N extends INode, E extends IEdge>
{
    /**
     * identifier of the graph
     *
     * @return id
     */
    @NonNull
    String id();

    /**
     * checks if a node exists
     *
     * @param p_id id of the node
     * @return existing flag
     */
    boolean containsnode( @NonNull final String p_id );

    /**
     *
     * @param p_id
     * @return
     */
    boolean containsedge( @NonNull final String p_id );

    E edge( @NonNull final String p_id );

    N node( @NonNull final String p_id );

    IGraph<N, E> add( @NonNull final N p_start, @NonNull final N p_end, @NonNull final E p_edge );

    /**
     * stream over all edges
     *
     * @return edge stream
     */
    @NonNull
    Stream<? extends E> edges();

    /**
     * stream over all nodes
     *
     * @return node stream
     */
    @NonNull
    Stream<? extends N> nodes();
}
