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

package de.tu_clausthal.in.mec.modeling.model.petri;

import de.tu_clausthal.in.mec.modeling.model.IModel;
import de.tu_clausthal.in.mec.modeling.model.graph.IGraph;
import de.tu_clausthal.in.mec.modeling.model.graph.jung.CDirectedGraph;
import edu.umd.cs.findbugs.annotations.NonNull;


/**
 * petrinet model
 */
public final class CPetrinet implements IPetrinet
{
    /**
     * graph structure
     */
    private final IGraph<IPlace, ILinkage> m_network;

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
        return false;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public <N extends IModel<IPetrinet>> N raw()
    {
        return (N) this;
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
}
