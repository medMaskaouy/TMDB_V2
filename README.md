# TMDB_V2
This is a simple movies viewer application, it uses : 

 	 MVVM aritechture
		Dagger Hilt for dependency Injection
	 Room for the local DB
		Live Data
	 Flow to read from the DB
		A custom Delegate Adapter instead of the normal recyclerView Adapter that uses composition and can easly used with different viewTypes
		Viewbinding
	 Lottie annimation
	 Chuck to debug the network calls
	 supports Pagination
	 delegate design patter implemented for the adapter, viewbinding and also  for the progressBar.
	
	And other custom extensions.
	
in the main screen we can search for a spec movie title, we can scroll down to see all the search result, clicking on a particular movie we cal add
it the to favorite section, in the menu area we can navigate to the favorite page and see all the favorite movies loaded from a local dataBase.



TODO : 
	# Add Sorting Functionalities : sort by date  and Alphabeticaly
	# Add testing with BBD, Junit 5
	# Support Full Offline Mode.
