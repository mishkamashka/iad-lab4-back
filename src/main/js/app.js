const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {points: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/points'}).done(response => {
            this.setState({points: response.entity._embedded.points});
    });
    }

    render() {
        return (
            <PointsList points={this.state.points}/>
    )
    }
}