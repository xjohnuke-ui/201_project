document.addEventListener('DOMContentLoaded', function () {
    const categoryItems = document.querySelectorAll('.category-item');
    categoryItems.forEach(item => {
        item.addEventListener('click', function () {
            // 移除所有 li 的 active 类
            categoryItems.forEach(item => {
                item.classList.remove('active');
                const link=item.querySelector('a');
                link.style.color='';
            });
            // 给当前点击的 li 添加 active 类
            this.classList.add('active');
            const link=this.querySelector('a');
            link.style.color='#ff0036';
            event.stopPropagation();
            // // event.preventDefault();
        });
    });
})