<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="resources/js/materialize.js"></script>
<script src="resources/js/init.js"></script>
<script>
	$(document).ready(function() {
		$('select').material_select();
	});



	$("select").change(function(){
		var val=parseInt($(this).val());
            if(val>1000) {
				if(val>1100) {
					$('#field'+(val-1100)).show();
				} else {
					if(val >1040) {
						$('#field'+(val-1040)).hide();
					} else if(val >1020) {
						$('#field'+(val-1020)).hide();
					} else if(val >1000) {
						$('#field'+(val-1000)).hide();
					}
				}
			}
       });
</script>
