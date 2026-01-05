
    function updateQuantity(change) {
    const input = document.querySelector('.quantity-input');
    let value = parseInt(input.value) + change;
    if (value < 1) value = 1;
    if (value > 99) value = 99;
    input.value = value;
}

    // 获取表单和按钮元素
    const form = document.getElementById('myForm');
    const buyNowButton = document.querySelector('.buy-now');
    const addToCartButton = document.querySelector('.add-to-cart');

    // 点击 "立即购买" 按钮时
    buyNowButton.addEventListener('click', function() {
    const quantity = document.querySelector('.quantity-input').value;
    const goodsId = document.querySelector('.goods_id').value; // 获取商品 ID

    // 修改 form 的 action 地址为 "立即购买" 路径
    form.action = `/shopping/orderInfo/add?id=${goodsId}&quantity=${quantity}`;

    // 提交表单
    form.submit();
});

    // 点击 "加入购物车" 按钮时
    addToCartButton.addEventListener('click', function() {
    const quantity = document.querySelector('.quantity-input').value;
    const goodsId = document.querySelector('.goods_id').value; // 获取商品 ID

    // 修改 form 的 action 地址为 "加入购物车" 路径
    form.action = `/shopping/detail?goods_id=${goodsId}&quantity=${quantity}`;

    // 提交表单
    form.submit();
});
