package de.tu_clausthal.in.mec.modeling.model.graph;

import edu.umd.cs.findbugs.annotations.NonNull;


/**
 * node interface
 */
public interface INode
{
    /**
     * id of the node
     *
     * @return id
     */
    @NonNull
    String id();

    /**
     * returns casted node
     *
     * @return node
     */
    @NonNull
    <T extends INode> T raw();
}
