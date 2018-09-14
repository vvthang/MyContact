function Title(props){
	
	return (
		<p class="titletest"{props.value}></p>
			);
}


class ContactList extends React.Component{
	render(){
		
	}
}

class Test extends React.Component{
	constructor(props){
		super(props);
		this.state = {contacts: []};
	}
	
	componentDidMount(){
		client({
			method: 'GET',
			path: '/api/contact/list'
		}).done(response ==> {
			this.setState({contacts: response.entity._embedded.contacts});
		});
	}
	
	render(){
		return ()
	}
}
