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

import de.tu_clausthal.in.mec.modeling.model.petrinet.CPetrinetChecker;
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

    /**
     * add transition
     *
     * @param p_net name
     * @return petrinet structure
     */
    @RequestMapping( value = "/transition/{net}/{transition}" )
    public Object transition( @PathVariable( "net" ) final String p_net, @PathVariable( "transition" ) final String p_transition )
    {
        return EModelStorage.INSTANCE.apply( p_net ).<IPetrinet>raw().addTransition( p_transition ).serialize();
    }

    @RequestMapping( value = "/connect/{net}/{connection}/{source}/{target}/{capacity}" )
    public Object transition( @PathVariable( "net" ) final String p_net, @PathVariable( "connection" ) final String p_connection,
                              @PathVariable( "source" ) final String p_source, @PathVariable( "target" ) final String p_target,
                              @PathVariable( "capacity" ) final Number p_capacity )
    {
        return EModelStorage.INSTANCE.apply( p_net ).<IPetrinet>raw().connect( p_connection, p_source, p_target, p_capacity ).serialize();
    }

    /**
     * execute model checking
     *
     * @return hcecking result
     */
    @RequestMapping( value = "/check/{target}/{source}" )
    public Object check( @PathVariable( "target" ) final String p_target, @PathVariable( "source" ) final String p_source )
    {
        return new CPetrinetChecker().apply(
            EModelStorage.INSTANCE.apply( p_target ).raw(),
            EModelStorage.INSTANCE.apply( p_source ).raw()
        );
    }
}
