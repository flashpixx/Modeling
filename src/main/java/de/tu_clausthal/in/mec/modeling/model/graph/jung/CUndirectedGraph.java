/*
 * @cond LICENSE
 * ######################################################################################
 * # LGPL License                                                                       #
 * #                                                                                    #
 * # This file is part of the TU-Clausthal Mobile and Enterprise Computing Modeling     #
 * # Copyright (c) 2018-19                                                              #
 * # This program is free software: you can redistribute it and/or modify               #
 * # it under the terms of the GNU Lesser General Public License as                     #
 * # published by the Free Software Foundation, either version 3 of the                 #
 * # License, or (at your option) any later version.                                    #
 * #                                                                                    #
 * # This program is distributed in the hope that it will be useful,                    #
 * # but WITHOUT ANY WARRANTY; without even the implied warranty of                     #
 * # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                      #
 * # GNU Lesser General Public License for more details.                                #
 * #                                                                                    #
 * # You should have received a copy of the GNU Lesser General Public License           #
 * # along with this program. If not, see http://www.gnu.org/licenses/                  #
 * ######################################################################################
 * @endcond
 */

package de.tu_clausthal.in.mec.modeling.model.graph.jung;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.umd.cs.findbugs.annotations.NonNull;


/**
 * undirected graph
 *
 * @tparam N node type
 * @tparam E edge type
 */
public final class CUndirectedGraph<N extends INode, E extends IEdge> extends IBaseGraph<N, E>
{

    /**
     * ctor
     *
     * @param p_name name / identifier
     */
    public CUndirectedGraph( @NonNull final String p_name )
    {
        super( p_name, new UndirectedSparseGraph<>() );
    }

}
