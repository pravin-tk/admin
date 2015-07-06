$(function () {
    // Tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Date picker
    $('.datepickerHolder input').datepicker({
        format: "dd/mm/yyyy",
        autoclose: true
    });

    /* payment mode hide/show */

    $("#checkMode").show();
    $("input[name$='payment-mode']").click(function () {
        var test = $(this).val();
        $(".display-none").stop().slideUp();
        $("#" + test).stop().slideDown();
        $('html,body').animate({
            scrollTop: $("#" + test).offset().top
        }, 'slow');
    });

    $(".text-line").click(function () {
        if (($(".main-div:visible").length) > 0) {
            $(".main-div:visible").slideDown();
        }
        $(this).next(".main-div").slideToggle();
        /*var iD = $(this).next().find('.text-edit').attr('id');
			if(CKEDITOR.instances){
				for (name in CKEDITOR.instances) {
					CKEDITOR.instances[name].destroy()
				}
			}
			if(curId != iD){
				CKEDITOR.replace(iD, {
					height: 500,
					width: '100%'
				});
				console.log(iD);
			}else{
				curId = 0;
			}
			curId = iD;*/
        //CKEDITOR.replace( iD );

        //alert(iD);
    });

    /* Add a new tier - Commission values hide/show */

    //$('.platformSpecific').hide();
    $('#commissionType').change(function () {
        if ($('#commissionType').val() === 'platformSpecific') {
            $('.platformSpecific').stop().slideDown();
        } else {
            $('.platformSpecific').stop().slideUp();
        }
    });

    /* Add a new tier - Commission values hide/show */
    $('#modalCommissionType').change(function () {
        if ($(this).val() === "regular") {
            //$('#modalCommissionTypeRegular').modal('show')
            $('#commissionTypeRegular').stop().slideDown();
            $('#commissionTypeTiered').stop().slideUp();
            $('html,body').animate({
                scrollTop: $("#commissionTypeRegular").offset().top
            }, 'slow');
        }
    });

    $('#modalCommissionType').change(function () {
        if ($(this).val() === "tiered") {
            $('#commissionTypeRegular').stop().slideUp();
            $('#commissionTypeTiered').stop().slideDown();
            $('html,body').animate({
                scrollTop: $("#commissionTypeTiered").offset().top
            }, 'slow');
        }
    });

    // Datatables

    $('#logs-table, #invoice-history-table, #vendors-table, #commission-table,#school-timeline-table, #contacts-table,#school-management-table #fees-subscriptions-table,#highlight-table-data, #prevStudent-table').dataTable();

    // Configurations - Subscriptions tab

    $(".view-id").click(function () {
        $(".subscriptions-list").hide('fast', function () {
            $(".subscriptions-new").fadeIn();
        });
    });
    $(".list-id").click(function () {
        $(".subscriptions-new").hide('fast', function () {
            $(".subscriptions-list").fadeIn();
        });
    });
    $(".view-id").click(function () {
        $(".subscriptions-list").hide('fast', function () {
            $(".subscriptions-new").fadeIn();
        });
    });
    $('#feeType').change(function () {
        if ($('#feeType').val() === 'subscription') {
            $('.billing-frequency').stop().slideDown();
        } else {
            $('.billing-frequency').stop().slideUp();
        }
    });

    // JS added by khalid 
    $(".new-commission").click(function () {
        $(".commission-list").hide('fast', function () {
            $(".commission-new").fadeIn();
        });
    });
    $(".list-commission").click(function () {
        $(".commission-new").hide('fast', function () {
            $(".commission-list").fadeIn();
        });
    });

    $(".view-contacts").click(function () {
        $(".contacts-list").hide('fast', function () {
            $(".contacts-new").fadeIn();
        });
    });
    $(".list-contacts").click(function () {
        $(".contacts-new").hide('fast', function () {
            $(".contacts-list").fadeIn();
        });
    });
    
    $(".view-school-management").click(function () {
        $(".school-management-list").hide('fast', function () {
            $(".school-management-new").fadeIn();
        });
    });
    $(".list-school-management").click(function () {
        $(".school-management-new").hide('fast', function () {
            $(".school-management-list").fadeIn();
        });
    });
    
    $(".list-school-timeline").click(function () {
        $(".school-timeline-new").hide('fast', function () {
            $(".school-timeline-list").fadeIn();
        });
    });
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
    $(".view-class-detail").click(function () {
        $(".class-detail-list").hide('fast', function () {
            $(".class-detail-new").fadeIn();
        });
    });
    
    $(".cancel-class-detail").click(function () {
        $(".class-detail-new").hide('fast', function () {
            $(".class-detail-list").fadeIn();
        });
    });
    
    $(".view-contact").click(function () {
        $(".contact-list").hide('fast', function () {
            $(".contact-new").fadeIn();
            
        });
    });
    $(".list-contact").click(function () {
        $(".contact-new").hide('fast', function () {
            $(".contact-list").fadeIn();           
        });
    });
    
    
    $(".view-prevStudent").click(function () {
        $(".prevStudent-list").hide('fast', function () {
            $(".prevStudent").fadeIn();
            
        });
    });
    $(".list-prevStudent").click(function () {
        $(".prevStudent").hide('fast', function () {
            $(".prevStudent-list").fadeIn();           
        });
    });
    
    $(".list-sales").click(function () {
        $(".sales-new").hide('fast', function () {
            $(".sales-list").fadeIn();           
        });
    });
    $(".view-sales").click(function () {
        $(".sales-list").hide('fast', function () {
            $(".sales-new").fadeIn();
            
        });
    });
    
    $(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
        var target = this.href,
            t = target.split('#');
        $("." + t[1] + "-new").hide();
        $("." + t[1] + "-list").show();
        console.log("Tab")
    });

    // Add tier - add-specific-commission

    $(".add-specific-commission").click(function () {
        if ($('.add-specific').length < 3) {
            $('.add-column').clone().addClass('add-specific').insertAfter(".add-column").removeClass("add-column");
        }
    });

    $(".add-tiered-specific-commission").click(function () {
        if ($('.add-tiered').length < 3) {
            $('.add-tiered-column').clone().addClass('add-tiered').insertAfter(".add-tiered-column").removeClass("add-tiered-column");
        }
    });
    
    jQuery(function($) {
		var slide = false;
		var height = $('#footer_content').height();
		$('#footer_button').click(function() {
			var docHeight = $(document).height();
			var windowHeight = $(window).height();
			var scrollPos = docHeight - windowHeight + height;
			$('#footer_content').animate({ height: "toggle"}, 1000);
			if(slide == false) {
				if($.browser.opera) {
					$('html').animate({scrollTop: scrollPos+'px'}, 1000);
				} else {
					$('html, body').animate({scrollTop: scrollPos+'px'}, 1000);
				}
				slide = true;
			} else {
				slide = false;
			}
		});
	});


});