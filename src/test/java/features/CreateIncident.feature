Feature: Incident Managemant

  Scenario: Create a new incident with short description
	Given enable logs
	And short description is added with 'Added from Cucumber'
	When new incident is created
	Then the status code is 201
	And response includes the following
	#DataTable
	#Map => Key = value
	# path  -> Value
	|result.sys_created_by|admin| 
	|result.sys_class_name|incident|
	
  Scenario: Create a new incident with description
	Given enable logs
	And description is added with Cucumber
	When new incident is created
	Then the status code is 201
	And response includes the following
	|result.sys_created_by|admin|
	|result.sys_class_name|incident|
	
	
	
	
	
	
	