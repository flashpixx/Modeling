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

package de.tu_clausthal.in.mec.modeling.model.graph;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;


/**
 * abstract class of an node
 */
public abstract class IBaseNode implements INode
{
    /**
     * id of the node
     */
    @JsonProperty( "id" )
    protected final String m_id;

    /**
     * ctor
     *
     * @param p_id edge id
     */
    protected IBaseNode( @NonNull final String p_id )
    {
        m_id = p_id;
    }

    @Override
    public final int hashCode()
    {
        return m_id.hashCode();
    }

    @Override
    public final boolean equals( final Object p_object )
    {
        return p_object instanceof INode && p_object.hashCode() == this.hashCode();
    }

    @NonNull
    @Override
    public final String id()
    {
        return m_id;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public final <T extends INode> T raw()
    {
        return (T) this;
    }
}
