package de.tu_clausthal.in.mec.modeling.model.graph;

import edu.umd.cs.findbugs.annotations.NonNull;


/**
 * interface of a graph factory
 */
public interface IGraphFactory
{
    /**
     * generates a graph
     *
     * @param p_name name / identifier of the graph
     *
     * @tparam N node type
     * @tparam E edge type
     * @return graph object
     */
    <N extends INode, E extends IEdge> IGraph<N, E> get( @NonNull final String p_name );

}
