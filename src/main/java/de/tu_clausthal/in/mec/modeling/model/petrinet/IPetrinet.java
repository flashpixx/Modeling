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

import de.tu_clausthal.in.mec.modeling.model.IModel;
import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnegative;


/**
 * interface of a petrinet
 */
public interface IPetrinet extends IModel<IPetrinet>
{
    /**
     * adds a transition
     *
     * @param p_id name
     * @return self-reference
     */
    IPetrinet addTransition( @NonNull final String p_id );

    /**
     * adds a place
     *
     * @param p_id name
     * @param p_capacity capacity
     * @return self-reference
     */
    IPetrinet addPlace( @NonNull final String p_id, @NonNull @Nonnegative final Number p_capacity );

    /**
     * connects two nodes (transition & place)
     *
     * @param p_id transition name
     * @param p_source id of the source
     * @param p_target id of the target
     * @param p_capacity transition capacity
     * @return self-reference
     */
    IPetrinet connect( @NonNull final String p_id, @NonNull final String p_source, @NonNull final String p_target, @NonNull @Nonnegative final Number p_capacity );

}
