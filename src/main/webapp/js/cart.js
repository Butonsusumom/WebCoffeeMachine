/* Set rates + misc */
var taxRate = 0.05;
var shippingRate = 15.00;
var fadeTime = 300;


/* Assign actions */
$('.product-quantity input').change( function() {
    updateQuantity(this);
});

$('#getTotalPrice').onclick( function() {
    recalculateCart(this);
});



/* Recalculate cart */
function recalculateCart()
{
    // var subtotal = 0;
    //
    // /* Sum up row totals */
    // $('.product').each(function () {
    //     subtotal += parseFloat($(this).children('.product-line-price').text());
    // });
    //
    // /* Update totals display */
    // $('.totals-value').fadeOut(fadeTime, function() {
    //     $('#cart-subtotal').html(subtotal.toFixed(2));
    // })
    $('#checkoutButton').show(1);
}


// /* Update quantity */
// function updateQuantity(quantityInput)
// {
//     /* Calculate line price */
//     var productRow = $(quantityInput).parent().parent();
//     var price = parseFloat(productRow.children('.product-price').text());
//     var quantity = $(quantityInput).val();
//     var linePrice = price * quantity;
//
//     /* Update line price display and recalc cart totals */
//     productRow.children('.product-line-price').each(function () {
//         $(this).fadeOut(fadeTime, function() {
//             $(this).text(linePrice.toFixed(2));
//             recalculateCart();
//             $(this).fadeIn(fadeTime);
//         });
//     });
// }


/* Remove item from cart */
// function removeItem(removeButton)
// {
//     // /* Remove row from DOM and recalc cart total */
//     // var productRow = $(removeButton).parent().parent();
//     // productRow.slideUp(fadeTime, function() {
//     //     productRow.remove();
//     //     recalculateCart();
//     // });
//
// }