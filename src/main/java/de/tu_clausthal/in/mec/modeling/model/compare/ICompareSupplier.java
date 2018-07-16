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

package de.tu_clausthal.in.mec.modeling.model.compare;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;

import javax.annotation.Nonnull;


/**
 * interface of graph comparators
 */
public interface ICompareSupplier
{

    /**
     * returns a comparator object
     *
     * @return comparator object
     *
     * @tparam N node type
     * @tparam E edge type
     */
    @Nonnull
    <N extends INode, E extends IEdge> ICompare<N, E> get();

}
