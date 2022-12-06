    console.log("hi");
        $(function() {
            $("#speciesName").autocomplete({
                maxResults: 10,
                source: "speciesNameAutocomplete",
                minLength: 3
            });
        });
