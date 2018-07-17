# Modellierungstool

* [Heroku Application](http://graphical-modelchecker.herokuapp.com/)
* [Dokumentation](https://omueller54.github.io/Modeling)

## Examples

### Petrinet

* create network
    * http://graphical-modelchecker.herokuapp.com/petrinet/create/foo
    * http://graphical-modelchecker.herokuapp.com/petrinet/place/foo/bar/5
    * http://graphical-modelchecker.herokuapp.com/petrinet/transition/foo/xxx
    * http://graphical-modelchecker.herokuapp.com/petrinet/connect/foo/trans/bar/xxx/100
    * http://graphical-modelchecker.herokuapp.com/model/remove/foo
    
    ```json
    {
      "edges":[
        {
          "from":"bar",
          "to":"xxx",
          "capacity":100
        }
      ],
        
      "places":{
        "bar":{
          "id":"bar",
          "marks":[],
          "capacity":5
        }
      },

      "transitions":{
        "xxx": {
          "id":"xxx"
        }
      }
    }
    ```