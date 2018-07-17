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

package de.tu_clausthal.in.mec.modeling.model.petrinet;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.mec.modeling.model.IModel;
import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.IGraph;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import de.tu_clausthal.in.mec.modeling.model.graph.jung.CDirectedGraph;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * petrinet model
 *
 * @todo call and terminate not implemented yet
 */
public final class CPetrinet implements IPetrinet
{
    /**
     * graph structure
     */
    private final IGraph<IPetrinetNode, IConnection> m_network;

    /**
     * ctor
     *
     * @param p_name name
     */
    public CPetrinet( @NonNull final String p_name )
    {
        m_network = new CDirectedGraph<>( p_name );
    }

    @Override
    public String id()
    {
        return m_network.id();
    }

    @Override
    public boolean terminated()
    {
        return true;
    }

    @NonNull
    @Override
    @SuppressWarnings( "unchecked" )
    public <N extends IModel<?>> N raw()
    {
        return (N) this;
    }

    @NonNull
    @Override
    public Object serialize()
    {
        return new CSerializer( m_network );
    }

    @Override
    public IPetrinet call() throws Exception
    {
        return this;
    }



    @Override
    public int hashCode()
    {
        return m_network.hashCode();
    }

    @Override
    public boolean equals( final Object p_object )
    {
        return p_object instanceof IModel<?> && p_object.hashCode() == this.hashCode();
    }

    @Override
    public IPetrinet addPlace( @NonNull final String p_id, @NonNull final Number p_capacity )
    {
        m_network.addnode( new CPlace( p_id, p_capacity ) );
        return this;
    }

    @Override
    public IPetrinet addTransitioin( @NonNull final String p_placebefore, @NonNull final String p_placeafter, @NonNull final Number p_capacitybefore,
                                     @NonNull final Number p_capacityafter )
    {
        return this;
    }

    /**
     * serializing class
     */
    private static final class CSerializer
    {
        /**
         * connections
         */
        @JsonProperty( "connection" )
        private final Map<Map.Entry<String, String>, Number> m_connection;
        /**
         * nodes
         */
        @JsonProperty( "places" )
        private final Set<IPlace> m_places;
        /**
         * transitions
         */
        @JsonProperty( "transitions" )
        private final Set<ITransition> m_transition;
        /**
         * edges
         */
        @JsonProperty( "edges" )
        private final Set<IEdge> m_edges;


        /**
         * ctor
         *
         * @param p_graph graph
         */
        CSerializer( @NonNull final IGraph<IPetrinetNode, IConnection> p_graph )
        {
            m_connection = null;
            m_edges = p_graph.edges().collect( Collectors.toSet() );

            m_places = p_graph.nodes()
                              .parallel()
                              .filter( i -> i instanceof IPlace )
                              .map( INode::<IPlace>raw )
                              .collect( Collectors.toSet() );

            m_transition = p_graph.nodes()
                                  .parallel()
                                  .filter( i -> i instanceof ITransition )
                                  .map( INode::<ITransition>raw )
                                  .collect( Collectors.toSet() );
        }
    }
}
