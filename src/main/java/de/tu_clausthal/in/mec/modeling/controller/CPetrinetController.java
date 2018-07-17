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

package de.tu_clausthal.in.mec.modeling.controller;

import de.tu_clausthal.in.mec.modeling.model.petrinet.IPetrinet;
import de.tu_clausthal.in.mec.modeling.model.storage.EModelStorage;
import de.tu_clausthal.in.mec.modeling.model.petrinet.CPetrinet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * petri-net rest controller
 */
@RestController
@RequestMapping( "/petrinet" )
public final class CPetrinetController
{
    /**
     * creates a new petrinet
     *
     * @param p_net name
     * @return created petrinet
     */
    @RequestMapping( value = "/create/{net}" )
    public Object create( @PathVariable( "net" ) final String p_net )
    {
        return EModelStorage.INSTANCE.add( new CPetrinet( p_net ) ).serialize();
    }

    /**
     * remove petrinet
     *
     * @param p_net name
     */
    @RequestMapping( value = "/remove/{net}" )
    public void remove( @PathVariable( "net" ) final String p_net )
    {
        EModelStorage.INSTANCE.remove( p_net );
    }

    /**
     * get petrinet
     *
     * @param p_net name
     * @return petrinet structure
     */
    @RequestMapping( value = "/get/{net}" )
    public Object get( @PathVariable( "name" ) final String p_net )
    {
        return EModelStorage.INSTANCE.apply( p_net ).serialize();
    }

    /**
     * add place
     *
     * @param p_net name
     * @return petrinet structure
     */
    @RequestMapping( value = "/place/{net}/{place}/{capacity}" )
    public Object place( @PathVariable( "net" ) final String p_net, @PathVariable( "place" ) final String p_place, @PathVariable( "capacity" ) final Number p_capacity )
    {
        return EModelStorage.INSTANCE.apply( p_net ).<IPetrinet>raw().addPlace( p_place, p_capacity ).serialize();
    }
}
